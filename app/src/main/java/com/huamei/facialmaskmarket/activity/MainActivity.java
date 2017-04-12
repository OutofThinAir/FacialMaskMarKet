package com.huamei.facialmaskmarket.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.huamei.facialmaskmarket.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        initView();
    }
    //初始化控件
    private void initView() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.main_pager_fl);
        RadioGroup rg = (RadioGroup) findViewById(R.id.main_pager_rg);
        RadioButton rb_home= (RadioButton) findViewById(R.id.main_pager_rb_home);
        RadioButton rb_classify= (RadioButton) findViewById(R.id.main_pager_rb_classify);
        RadioButton rb_special= (RadioButton) findViewById(R.id.main_pager_rb_speciel);
        RadioButton rb_shopping= (RadioButton) findViewById(R.id.main_pager_rb_shopping);
        RadioButton rb_mypager = (RadioButton) findViewById(R.id.main_pager_rb_my);
    }
}
