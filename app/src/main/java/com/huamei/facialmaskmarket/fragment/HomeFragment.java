package com.huamei.facialmaskmarket.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.huamei.facialmaskmarket.R;
import com.huamei.facialmaskmarket.bean.PicBean;
import com.huamei.facialmaskmarket.util.MyImageLoader;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.youth.banner.Banner;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Use:
 * Author:陈懿鹏
 * Data:2017/4/12.
 */

public class HomeFragment extends Fragment {

    private Banner banner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_pager_layout,null);
        initView(view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
      okHttpRequse("http://result.eolinker.com/x2nNYB9c021a181181c52c3a478b22043753a8c9a23d0d0?uri=轮播");
       //volleyRequse(getActivity(),"http://result.eolinker.com/x2nNYB9c021a181181c52c3a478b22043753a8c9a23d0d0?uri=轮播");
    }

    private void initView(View view){
        banner = (Banner) view.findViewById(R.id.home_pager_banner);
        ImageView towma= (ImageView) view.findViewById(R.id.home_pager_towma);
        TextView title= (TextView) view.findViewById(R.id.home_pager_title);
        ImageButton xiaoxi= (ImageButton) view.findViewById(R.id.home_pager_xiaoxi);



    }

    //Volley请求
    public  void volleyRequse(Context context, String url){
        //请求队列
        RequestQueue queue = Volley.newRequestQueue(context);
        //请求
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.d("wwwwwww",s.toString());
                Gson gson =new Gson();
                PicBean picBean = gson.fromJson(s,PicBean.class);
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i <picBean.getData().size() ; i++) {
                    PicBean.DataBean dataBean = picBean.getData().get(i);
                    list.add(dataBean.getPic());
                }
                Log.d("ddddddd",list.toString());
                //图片无线轮播
                banner.setImageLoader(new MyImageLoader());
                banner.setImages(list);
                banner.start();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(request);

    }

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
                PicBean picBean = gson.fromJson(s,PicBean.class);
                final ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i <picBean.getData().size() ; i++) {
                    PicBean.DataBean dataBean = picBean.getData().get(i);
                    list.add(dataBean.getPic());
                }
                Log.d("ddddddd",list.toString());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //图片无线轮播
                        banner.setImageLoader(new MyImageLoader());
                        banner.setImages(list);
                        banner.start();
                    }
                });

            }
        });

    }
}
