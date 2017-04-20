package com.huamei.facialmaskmarket.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huamei.facialmaskmarket.R;
import com.huamei.facialmaskmarket.activity.MinuteActivity;
import com.huamei.facialmaskmarket.adapter.HomeRvAdapter;
import com.huamei.facialmaskmarket.adapter.HomeRvAdapter02;
import com.huamei.facialmaskmarket.adapter.HomeRvAdapter03;
import com.huamei.facialmaskmarket.bean.HomeDataBean;
import com.huamei.facialmaskmarket.bean.PicBean;
import com.huamei.facialmaskmarket.util.MyImageLoader;
import com.huamei.facialmaskmarket.view.MyRecyclerView;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.youth.banner.Banner;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Use:
 * Author:陈懿鹏
 * Data:2017/4/12.
 */

public class HomeFragment extends Fragment {

    private Banner banner;
    private ImageView qiandao;
    private ImageView jfsc;
    private ImageView duihuan;
    private ImageView chaxun;
    private MyRecyclerView rcv01;
    private MyRecyclerView rcv02;
    private MyRecyclerView rcv03;
    private ImageView imageView;

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
        okHttpHomeData("http://m.yunifang.com/yunifang/mobile/home?random=84831&encode=9dd34239798e8cb22bf99a75d1882447");
    }

    private void initView(View view){
        banner = (Banner) view.findViewById(R.id.home_pager_banner);
        ImageView towma= (ImageView) view.findViewById(R.id.home_pager_towma);
        TextView title= (TextView) view.findViewById(R.id.home_pager_title);
        ImageButton xiaoxi= (ImageButton) view.findViewById(R.id.home_pager_xiaoxi);
        qiandao = (ImageView) view.findViewById(R.id.home_pager_hqd);
        jfsc = (ImageView) view.findViewById(R.id.home_pager_hjf);
        duihuan = (ImageView) view.findViewById(R.id.home_pager_hdh);
        chaxun = (ImageView) view.findViewById(R.id.home_pager_hcx);
        rcv01 = (MyRecyclerView) view.findViewById(R.id.home_pager_rcv01);
        rcv02 = (MyRecyclerView) view.findViewById(R.id.home_pager_rcv02);
        rcv03 = (MyRecyclerView) view.findViewById(R.id.home_pager_rcv03);
        imageView = (ImageView) view.findViewById(R.id.home_pager_rimage);

        //布局管理器
        StaggeredGridLayoutManager manager01 = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager manager02 = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        rcv01.setLayoutManager(manager01);
        rcv02.setLayoutManager(manager02);
        StaggeredGridLayoutManager manager03 = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        rcv03.setLayoutManager(manager03);






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

    //主题数据解析
    private void okHttpHomeData(String url){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request requse = new Request.Builder()
                .url(url)
                .build();
        final Call call=okHttpClient.newCall(requse);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {
                String s=response.body().string();
                Gson gson =new Gson();
                final HomeDataBean dataBean = gson.fromJson(s,HomeDataBean.class);
                final ArrayList<HomeDataBean.DataBean.BestSellersBean.GoodsListBeanX> list= (ArrayList<HomeDataBean.DataBean.BestSellersBean.GoodsListBeanX>) dataBean.getData().getBestSellers().get(0).getGoodsList();
                final ArrayList<HomeDataBean.DataBean.DefaultGoodsListBean> list2= (ArrayList<HomeDataBean.DataBean.DefaultGoodsListBean>) dataBean.getData().getDefaultGoodsList();
                final ArrayList<HomeDataBean.DataBean.SubjectsBean.GoodsListBean> list3 = (ArrayList<HomeDataBean.DataBean.SubjectsBean.GoodsListBean>) dataBean.getData().getSubjects().get(0).getGoodsList();
                Log.d("wwwwwwwww",list.toString());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //每日签到等
                        Glide.with(getActivity()).load(dataBean.getData().getAd5().get(0).getImage()).into(qiandao);
                        Glide.with(getActivity()).load(dataBean.getData().getAd5().get(1).getImage()).into(jfsc);
                        Glide.with(getActivity()).load(dataBean.getData().getAd5().get(2).getImage()).into(duihuan);
                        Glide.with(getActivity()).load(dataBean.getData().getAd5().get(3).getImage()).into(chaxun);
                        Glide.with(getActivity()).load(dataBean.getData().getSubjects().get(0).getImage()).into(imageView);

                        //数据适配
                        HomeRvAdapter02 adapter02 = new HomeRvAdapter02(getActivity(),list);
                        rcv01.setAdapter(adapter02);
                        adapter02.notifyDataSetChanged();

                        HomeRvAdapter03 adapter03 = new HomeRvAdapter03(getActivity(),list2);
                        rcv02.setAdapter(adapter03);
                        adapter03.notifyDataSetChanged();

                        HomeRvAdapter adapter = new HomeRvAdapter(getActivity(),list3);
                        rcv03.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        //rcv03条目点击事件
                        adapter.setOnItemLongClickListener(new HomeRvAdapter.OnItemLongClickListener() {
                            @Override
                            public void onItemLongClick(View view, int position) {
                                Intent intent = new Intent(getActivity(), MinuteActivity.class);
                                intent.putExtra("id",list3.get(position).getId());
                                startActivity(intent);
                            }
                        });

                    }
                });


            }
        });
    }
}
