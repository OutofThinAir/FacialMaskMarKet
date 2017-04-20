package com.huamei.facialmaskmarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.gson.Gson;
import com.huamei.facialmaskmarket.R;
import com.huamei.facialmaskmarket.adapter.GongXiaoBeanAdapter;
import com.huamei.facialmaskmarket.bean.ClassifyGongXioBean;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Use:
 * Author:陈懿鹏
 * Data:2017/4/19.
 */

public class ClassifyMinuteFragment extends Fragment {

    private GridView gridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.classify_minute_fg_layout,null);
        //初始化控件
        gridView = (GridView) view.findViewById(R.id.cl_mi_fg_gv);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        String name = bundle.getString("name");
        if (name.equals("补水保湿")){
            okHttpRequse("http://m.yunifang.com/yunifang/mobile/goods/getall?random=60305&encode=b0358434fda8d7478bfc325b5828b721&category_id=17");

        }
        if (name.equals("舒缓修复")){
            okHttpRequse("http://m.yunifang.com/yunifang/mobile/goods/getall?random=59154&encode=4f90b7d8723b9819b90acf2a845abe98&category_id=18");


        }
        if (name.equals("控油祛痘")){
            okHttpRequse("http://m.yunifang.com/yunifang/mobile/goods/getall?random=47920&encode=d4b0bb7403d31c66f22c33397ad896e3&category_id=33");


        }
        if (name.equals("美白提亮")){
            okHttpRequse("http://m.yunifang.com/yunifang/mobile/goods/getall?random=83560&encode=3108ed0b9a42c1e160b2912a78692263&category_id=9");

        }
        if (name.equals("紧致抗皱")){
            okHttpRequse("http://m.yunifang.com/yunifang/mobile/goods/getall?random=50464&encode=789002e73efb3ab24ecd56d42df3ef49&category_id=19");

        }

    }


    //生成fragment的方法
    public static ClassifyMinuteFragment newInstent(String name){
        Bundle bundleb=new Bundle();
        bundleb.putString("name",name);
        ClassifyMinuteFragment fragment = new ClassifyMinuteFragment();
        fragment.setArguments(bundleb);
        return fragment;
    }

    //数据请求
    //网络请求
    private void okHttpRequse(String url){
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
                ClassifyGongXioBean gongXioBean = gson.fromJson(s,ClassifyGongXioBean.class);
                final ArrayList<ClassifyGongXioBean.DataBean> list = (ArrayList<ClassifyGongXioBean.DataBean>) gongXioBean.getData();



                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        GongXiaoBeanAdapter adapter = new GongXiaoBeanAdapter(getActivity(),list);
                        gridView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        });

    }
}
