package com.huamei.facialmaskmarket.activity;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.huamei.facialmaskmarket.R;
import com.huamei.facialmaskmarket.fragment.ClassifyFragment;
import com.huamei.facialmaskmarket.fragment.HomeFragment;
import com.huamei.facialmaskmarket.fragment.MyFragment;
import com.huamei.facialmaskmarket.fragment.ShoppingFragment;
import com.huamei.facialmaskmarket.fragment.SpecialFragment;

public class MainActivity extends FragmentActivity {

    private FragmentManager manager;
    private HomeFragment homeFragment;
    private ClassifyFragment classifyFragment;
    private SpecialFragment specialFragment;
    private ShoppingFragment shoppingFragment;
    private MyFragment myFragment;
    private RadioGroup rg;
    private RadioButton rb_home;
    private RadioButton rb_classify;
    private RadioButton rb_special;
    private RadioButton rb_shopping;
    private RadioButton rb_mypager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //初始化控件
        initView();
        //初始化FragMent
        initFrag();
        //默认选中第一个
        rg.check(R.id.main_pager_rb_home);
        //RadioGroup监听
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_pager_rb_home:
                        cutFragment(homeFragment,specialFragment,classifyFragment,shoppingFragment,myFragment);
                        break;
                    case R.id.main_pager_rb_classify:
                        cutFragment(classifyFragment,homeFragment,specialFragment,shoppingFragment,myFragment);
                        break;
                    case R.id.main_pager_rb_speciel:
                        cutFragment(specialFragment,homeFragment,classifyFragment,shoppingFragment,myFragment);
                        break;
                    case R.id.main_pager_rb_shopping:
                        cutFragment(shoppingFragment,homeFragment,classifyFragment,specialFragment,myFragment);
                        break;
                    case R.id.main_pager_rb_my:
                        cutFragment(myFragment,homeFragment,classifyFragment,shoppingFragment,specialFragment);
                        break;
                }
            }
        });
    }
    //初始化控件
    private void initView() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.main_pager_fl);
        rg = (RadioGroup) findViewById(R.id.main_pager_rg);
        rb_home = (RadioButton) findViewById(R.id.main_pager_rb_home);
        rb_classify = (RadioButton) findViewById(R.id.main_pager_rb_classify);
        rb_special = (RadioButton) findViewById(R.id.main_pager_rb_speciel);
        rb_shopping = (RadioButton) findViewById(R.id.main_pager_rb_shopping);
        rb_mypager = (RadioButton) findViewById(R.id.main_pager_rb_my);
    }

    //添加Fragment
    private void initFrag(){
        homeFragment = new HomeFragment();
        classifyFragment = new ClassifyFragment();
        specialFragment = new SpecialFragment();
        shoppingFragment = new ShoppingFragment();
        myFragment = new MyFragment();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_pager_fl,homeFragment,"hf");
        transaction.add(R.id.main_pager_fl,classifyFragment,"cf");
        transaction.add(R.id.main_pager_fl,specialFragment,"spf");
        transaction.add(R.id.main_pager_fl,shoppingFragment,"shf");
        transaction.add(R.id.main_pager_fl,myFragment,"mf");

        transaction.hide(classifyFragment);
        transaction.hide(specialFragment);
        transaction.hide(shoppingFragment);
        transaction.hide(myFragment);
        transaction.commit();

    }

    private void cutFragment(Fragment newFragment,Fragment oldFragment01,Fragment oldFragment02
    ,Fragment oldFragment03,Fragment oldFragment04){
        FragmentTransaction transaction = manager.beginTransaction();
       transaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        transaction.show(newFragment);
        transaction.hide(oldFragment01);
        transaction.hide(oldFragment02);
        transaction.hide(oldFragment03);
        transaction.hide(oldFragment04);
        transaction.commit();
    }


}
