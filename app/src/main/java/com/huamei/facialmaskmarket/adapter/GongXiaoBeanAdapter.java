package com.huamei.facialmaskmarket.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huamei.facialmaskmarket.R;
import com.huamei.facialmaskmarket.bean.ClassifyGongXioBean;

import java.util.ArrayList;

/**
 * Use:
 * Author:陈懿鹏
 * Data:2017/4/19.
 */

public class GongXiaoBeanAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ClassifyGongXioBean.DataBean> list;

    public GongXiaoBeanAdapter(Context context, ArrayList<ClassifyGongXioBean.DataBean> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder ;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.classify_minute_gv_it_lay,null);
            holder=new MyViewHolder();
            holder.imageView= (ImageView) convertView.findViewById(R.id.cl_mi_gv_iamge);
            holder.textView= (TextView) convertView.findViewById(R.id.cl_mi_gv_price);
            convertView.setTag(holder);
        }else {
            holder= (MyViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getGoods_img()).into(holder.imageView);
        holder.textView.setText("￥"+list.get(position).getShop_price());
        return convertView;
    }

    private class MyViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
