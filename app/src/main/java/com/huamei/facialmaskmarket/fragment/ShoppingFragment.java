package com.huamei.facialmaskmarket.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huamei.facialmaskmarket.R;
import com.huamei.facialmaskmarket.adapter.ShopCarAdapter;
import com.huamei.facialmaskmarket.bean.PicBean;
import com.huamei.facialmaskmarket.bean.ShopCarBean;
import com.huamei.facialmaskmarket.util.MyImageLoader;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Use:
 * Author:陈懿鹏
 * Data:2017/4/12.
 */

public class ShoppingFragment extends Fragment {

    private ListView listView;
    public static CheckBox quanxuan;
    private TextView jiesuan;
    public static TextView zongjia;
    private int uid;
    private ShopCarAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopping_pager_layout,null);
        initView(view);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPreferences pre = getActivity().getSharedPreferences("cofig", Context.MODE_PRIVATE);
        uid = pre.getInt("uid",-1);
        selectShopCar(uid);


    }

    //初始化控件
    private void initView(View view){

        listView = (ListView) view.findViewById(R.id.shop_pager_list);
        quanxuan = (CheckBox) view.findViewById(R.id.shop_car_quanxuan);
        jiesuan = (TextView) view.findViewById(R.id.shop_car_jiesuan);
        zongjia = (TextView) view.findViewById(R.id.shop_car_zongja);




    }



    public void selectShopCar(int userId){
        String url = "http://169.254.217.5:8080/bullking1/cart?userID="+userId;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request requse = new Request.Builder()
                .url(url)
                .build();
        Call call=okHttpClient.newCall(requse);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {
                String s=response.body().string();
                Gson gson =new Gson();
                ShopCarBean bean = gson.fromJson(s,ShopCarBean.class);
                final ArrayList<ShopCarBean.CartItemListBean> list = (ArrayList<ShopCarBean.CartItemListBean>) bean.getCartItemList();

                getActivity().runOnUiThread(new Runnable() {



                    @Override
                    public void run() {
                        adapter = new ShopCarAdapter(getActivity(),list);
                        listView.setAdapter(adapter);


                        //全选按钮的判断
                        quanxuan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                boolean checked = ((CheckBox) v).isChecked();
                                if (checked==true) {
                                    adapter.setCheck(true, list);

                                }else{
                                    adapter.setCheck(false,list);

                                }
                                double price=0;
                                for (int i = 0; i <list.size() ; i++) {
                                    if(list.get(i).isFlag()){
                                        price+=list.get(i).getPrice();

                                    }else{
                                        price=0;
                                    }

                                }
                                adapter.setPrice(price);
                                zongjia.setText("￥"+price);
                                adapter.notifyDataSetChanged();
                            }
                        });



                    }
                });

            }
        });


    }

    //fragment切换

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            selectShopCar(uid);

        }
    }
}
