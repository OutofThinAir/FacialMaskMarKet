package com.huamei.facialmaskmarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huamei.facialmaskmarket.R;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopping_pager_layout,null);
        return view;

    }
    //初始化控件
    private void initView(View view){
        ListView listView= (ListView) view.findViewById(R.id.shop_pager_list);
        CheckBox quanxuan= (CheckBox) view.findViewById(R.id.shop_car_quanxuan);
        TextView jiesuan = (TextView) view.findViewById(R.id.shop_car_jiesuan);
        TextView zongjia = (TextView) view.findViewById(R.id.shop_car_zongja);


    }

    private void selectShopCar(int userId){
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
                ArrayList<ShopCarBean.CartItemListBean> list = (ArrayList<ShopCarBean.CartItemListBean>) bean.getCartItemList();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });

            }
        });


    }

}
