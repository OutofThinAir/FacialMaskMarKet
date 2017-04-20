package com.huamei.facialmaskmarket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huamei.facialmaskmarket.R;
import com.huamei.facialmaskmarket.bean.HomeDataBean;

import java.util.ArrayList;

/**
 * Use:
 * Author:陈懿鹏
 * Data:2017/4/17.
 */

public class HomeRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<HomeDataBean.DataBean.SubjectsBean.GoodsListBean> list;
    private AdapterView.OnItemClickListener onItemClickListener;
   private AdapterView.OnItemLongClickListener onItemLongClickListener;
   private  ArrayList<Integer> mHeights;


    public HomeRvAdapter(Context context, ArrayList<HomeDataBean.DataBean.SubjectsBean.GoodsListBean> list) {
        this.context = context;
        this.list = list;
        initmHeights();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //初始化布局
        View view = LayoutInflater.from(context).inflate(R.layout.home_pg_rv_item_lay02,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            MyViewHolder holder1 = (MyViewHolder) holder;

            ViewGroup.LayoutParams lp = holder1.imageView.getLayoutParams();

//            lp.height = mHeights.get(position);
//            holder1.imageView.setLayoutParams(lp);
//            holder1.title.setLayoutParams(lp);
//            holder1.price.setLayoutParams(lp);
            Glide.with(context).load(list.get(position).getGoods_img()).into(holder1.imageView);
            holder1.title.setText(list.get(position).getGoods_name());
            holder1.price.setText(list.get(position).getShop_price()+"元");







        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class  MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;
        TextView price;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.imageView= (ImageView) itemView.findViewById(R.id.home_pg_rv_iamge02);
            this.title = (TextView) itemView.findViewById(R.id.home_pg_rv_title02);
            this.price = (TextView) itemView.findViewById(R.id.home_pg_rv_price02);

        }
    }

    //初始化随机数集合
    private void initmHeights(){
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++)
        {
            mHeights.add( (int) (100 + Math.random() * 300));
        }
    }
}
