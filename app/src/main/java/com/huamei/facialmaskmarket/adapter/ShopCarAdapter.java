package com.huamei.facialmaskmarket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huamei.facialmaskmarket.R;
import com.huamei.facialmaskmarket.activity.MainActivity;
import com.huamei.facialmaskmarket.bean.PicBean;
import com.huamei.facialmaskmarket.bean.ShopCarBean;
import com.huamei.facialmaskmarket.fragment.ShoppingFragment;
import com.huamei.facialmaskmarket.util.MyImageLoader;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Use:
 * Author:陈懿鹏
 * Data:2017/4/22.
 */

public class ShopCarAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ShopCarBean.CartItemListBean> list;
    private double price=0;


    public ShopCarAdapter(Context context, ArrayList<ShopCarBean.CartItemListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyHolder holder;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.shop_car_item_lay,null);
            holder=new MyHolder();
            holder.checkBox= (CheckBox) convertView.findViewById(R.id.shop_car_it_ch);
            holder.imageView= (ImageView) convertView.findViewById(R.id.shop_car_it_im);
            holder.title= (TextView) convertView.findViewById(R.id.shop_car_it_title);
            holder.price = (TextView) convertView.findViewById(R.id.shop_car_it_price);
            holder.delete= (TextView) convertView.findViewById(R.id.shop_car_it_del);
            convertView.setTag(holder);
        }else {
          holder= (MyHolder) convertView.getTag();
        }

        holder.title.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getPic()).into(holder.imageView);
        holder.price.setText("￥"+list.get(position).getPrice());

        //checkBox的监听
        holder.checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                list.get(position).setFlag(!list.get(position).isFlag());
                int quanxuan = quanxuan(list);
                if (quanxuan==0){
                    ShoppingFragment.quanxuan.setChecked(true);
                }else{
                    ShoppingFragment.quanxuan.setChecked(false);

                }
                if (quanxuan==list.size()){
                    price=0;
                }

                if (list.get(position).isFlag()==true){

                    price+=list.get(position).getPrice();
                }else{
                    price-=list.get(position).getPrice();
                    if (price<0){
                        price=0;
                    }
                }
            ShoppingFragment.zongjia.setText("￥"+price);

            }
        });
        holder.checkBox.setChecked(list.get(position).isFlag());

        //删除按钮监听
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okDelete(list.get(position).getProductID(),list,position);
                notifyDataSetChanged();

            }
        });
        return convertView;
    }

    private class MyHolder{
        CheckBox checkBox;
        ImageView imageView;
        TextView title;
        TextView price;
        TextView delete;
    }

    private int quanxuan(ArrayList<ShopCarBean.CartItemListBean> list){
        int count = 0;
        //设置全选,遍历集合
        for (int i = 0; i <list.size() ; i++) {
            if (list.get(i).isFlag()==false){
                count++;
            }
        }
        return count;
    }

    //所有全选
    public void setCheck(boolean f,ArrayList<ShopCarBean.CartItemListBean> list){
        for (int i = 0; i <list.size() ; i++) {
            list.get(i).setFlag(f);
        }
    }

    //设置价格的方法
    public void setPrice(double p){
        this.price=p;
    }

private void okDelete(int pid, final ArrayList<ShopCarBean.CartItemListBean> list, final int position){
    OkHttpClient okHttpClient = new OkHttpClient();
    Request requse = new Request.Builder()
            .url("http://169.254.217.5:8080/bullking1/deletepro?productID="+pid)
            .get()
            .build();
    Call call=okHttpClient.newCall(requse);
    call.enqueue(new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(com.squareup.okhttp.Response response) throws IOException {
            String s=response.body().string();
            try {
                JSONObject obj = new JSONObject(s);
                final int coun = obj.getInt("count");
               if (context instanceof MainActivity){
                   final MainActivity a = (MainActivity) context;
                   a.runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           if (coun!=0){
                               Toast.makeText(a, "删除成功", Toast.LENGTH_SHORT).show();
                               list.remove(position);
                               notifyDataSetChanged();
                           }else{
                               Toast.makeText(a, "删除失败", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
               }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    });
}

}
