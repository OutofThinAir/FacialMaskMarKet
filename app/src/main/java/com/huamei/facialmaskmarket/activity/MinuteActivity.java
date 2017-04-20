package com.huamei.facialmaskmarket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.TextView;

import com.huamei.facialmaskmarket.R;

/**
 * Use:
 * Author:陈懿鹏
 * Data:2017/4/20.
 */

public class MinuteActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minute_pager_layout);
        initView();
        Intent intent =getIntent();
        String id=intent.getStringExtra("id");

    }
    //初始化话控件
    private void initView(){
        ViewPager pager = (ViewPager) findViewById(R.id.minute_pager_vp);
        TextView title = (TextView) findViewById(R.id.minute_pager_title);
        TextView price = (TextView) findViewById(R.id.minute_pager_price);
        Button addShopp = (Button) findViewById(R.id.minute_pager_add_shoppcar);
        Button lijgoumai = (Button) findViewById(R.id.minute_pager_buy);

    }


}
