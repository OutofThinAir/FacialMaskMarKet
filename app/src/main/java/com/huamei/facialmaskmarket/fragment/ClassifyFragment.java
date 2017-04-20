package com.huamei.facialmaskmarket.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.huamei.facialmaskmarket.R;
import com.huamei.facialmaskmarket.activity.ClassifyMinuteActivity;
import com.huamei.facialmaskmarket.adapter.ClassRvAdapter;
import com.huamei.facialmaskmarket.bean.ClassifyDataBean;
import com.huamei.facialmaskmarket.bean.PicBean;
import com.huamei.facialmaskmarket.util.MyImageLoader;
import com.huamei.facialmaskmarket.view.MyRecyclerView;
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

public class ClassifyFragment extends Fragment implements View.OnClickListener{

    private MyRecyclerView rcv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classify_pager_layout,null);
        initView(view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
        okHttpRequse("http://m.yunifang.com/yunifang/mobile/category/list?random=96333&encode=bf3386e14fe5bb0bcef234baebca2414");
    }

    //初始化界面
    private void initView(View view){
        ImageView mianmo = (ImageView) view.findViewById(R.id.classify_pager_mm);
        ImageView rfs = (ImageView) view.findViewById(R.id.classify_pager_rfs);
        ImageView jmr = (ImageView) view.findViewById(R.id.classify_pager_jmr);
        ImageView shtz = (ImageView) view.findViewById(R.id.classify_pager_shtz);
        ImageView rfr = (ImageView) view.findViewById(R.id.classify_pager_rfr);
        ImageView qt = (ImageView) view.findViewById(R.id.classify_pager_qt);
        ImageView nszy = (ImageView) view.findViewById(R.id.classify_pager_nszy);
        //--------------------------------------------------------------------
        ImageView bsbs = (ImageView) view.findViewById(R.id.classify_pager_bsbs);
        ImageView shxf = (ImageView) view.findViewById(R.id.classify_pager_shxf);
        ImageView kyqd = (ImageView) view.findViewById(R.id.classify_pager_kyqd);
        ImageView mbtl = (ImageView) view.findViewById(R.id.classify_pager_mbtl);
        ImageView jzkz = (ImageView) view.findViewById(R.id.classify_pager_jzkz);
        //---------点击事件------------
        bsbs.setOnClickListener(this);
        shxf.setOnClickListener(this);
        kyqd.setOnClickListener(this);
        mbtl.setOnClickListener(this);
        jzkz.setOnClickListener(this);
        //--------------------------------------------------------------------------
        Button hhxfz = (Button) view.findViewById(R.id.classify_pager_hhxfz);
        Button zxzfz = (Button) view.findViewById(R.id.classify_pager_zxzfz);
        Button gzfz = (Button) view.findViewById(R.id.classify_pager_gzfz);
        Button yzfz = (Button) view.findViewById(R.id.classify_pager_yzfz);
        Button ddfz = (Button) view.findViewById(R.id.classify_pager_ddfz);
        Button mgfz = (Button) view.findViewById(R.id.classify_pager_mgfz);

        rcv = (MyRecyclerView) view.findViewById(R.id.classify_pager_rv);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        rcv.setLayoutManager(manager);
    }

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
                ClassifyDataBean dataBean = gson.fromJson(s,ClassifyDataBean.class);
                final ArrayList<ClassifyDataBean.DataBean.GoodsBriefBean> list= (ArrayList<ClassifyDataBean.DataBean.GoodsBriefBean>) dataBean.getData().getGoodsBrief();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //数据适配
                        ClassRvAdapter adapter = new ClassRvAdapter(getActivity(),list);
                        rcv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.classify_pager_bsbs:
                Intent intent = new Intent(getActivity(), ClassifyMinuteActivity.class);
                startActivity(intent);
                break;
            case R.id.classify_pager_shxf:
                break;
            case R.id.classify_pager_kyqd:
                break;
            case R.id.classify_pager_yzfz:
                break;
            case R.id.classify_pager_ddfz:
                break;
            case R.id.classify_pager_mgfz:
                break;
        }
    }
}
