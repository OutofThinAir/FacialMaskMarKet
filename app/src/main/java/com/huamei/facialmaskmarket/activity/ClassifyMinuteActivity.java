package com.huamei.facialmaskmarket.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.huamei.facialmaskmarket.R;
import com.huamei.facialmaskmarket.adapter.ClassRvAdapter;
import com.huamei.facialmaskmarket.bean.ClassifyDataBean;
import com.huamei.facialmaskmarket.fragment.ClassifyMinuteFragment;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Use:分类详情
 * Author:陈懿鹏
 * Data:2017/4/18.
 */

public class ClassifyMinuteActivity extends FragmentActivity {

    private ArrayList<String> title;
    private ArrayList<ClassifyMinuteFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classify_minute_layout);
        //初始化控件
        ImageView imageView = (ImageView) findViewById(R.id.classify_minute_last);
        TabLayout tab = (TabLayout) findViewById(R.id.classify_minute_tab);
        ViewPager pager = (ViewPager) findViewById(R.id.classify_minute_pager);

        //标题集合
        title = new ArrayList<>();
        title.add("补水保湿");
        title.add("舒缓修复");
        title.add("控油祛痘");
        title.add("美白提亮");
        title.add("紧致抗皱");

        fragments = new ArrayList<>();
        for (int i = 0; i < title.size() ; i++) {
            fragments.add(ClassifyMinuteFragment.newInstent(title.get(i)));
        }
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tab.setupWithViewPager(pager);

        //返回上一页
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //viewpager适配器
    private class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }
    }

}
