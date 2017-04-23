package com.huamei.facialmaskmarket.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huamei.facialmaskmarket.R;
import com.huamei.facialmaskmarket.bean.PicBean;
import com.huamei.facialmaskmarket.util.MyImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Use:
 * Author:陈懿鹏
 * Data:2017/4/21.
 */

public class LoginActivity extends Activity implements View.OnClickListener{

    private EditText into_uanme;
    private EditText into_pwd;
    private SharedPreferences pre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_pager_layout);
        initView();


    }
    //初始化控件
    private void initView(){
        ImageView last_pager= (ImageView) findViewById(R.id.login_pager_last);
        TextView register = (TextView) findViewById(R.id.login_pager_register);
        into_uanme = (EditText) findViewById(R.id.login_pager_into_uanme);
        into_pwd = (EditText) findViewById(R.id.login_pager_into_upwd);
        Button login= (Button) findViewById(R.id.login_pager_login_bu);
        last_pager.setOnClickListener(this);
        register.setOnClickListener(this);
        login.setOnClickListener(this);

        //获得登录状态
        pre = getSharedPreferences("cofig",MODE_PRIVATE);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_pager_last:
                //返回上一页
                finish();
                break;
            case R.id.login_pager_register:
                //跳转注册界面
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_pager_login_bu:




                if (!TextUtils.isEmpty(into_uanme.getText().toString())&&!TextUtils.isEmpty(into_pwd.getText().toString())){
                    //请求登录的接口
                    logPost(into_uanme.getText().toString(),into_pwd.getText().toString());
                }
               if(TextUtils.isEmpty(into_uanme.getText().toString())&&TextUtils.isEmpty(into_pwd.getText().toString())){
                   Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
               }




                break;

        }

    }

    //登录,请求后台
    private void  logPost(final String name, String pwd){

        OkHttpClient okhttp = new OkHttpClient();
        //请求体
        RequestBody body =  new FormBody.Builder()
                .add("name",name)
                .add("pwd",pwd)
                .build();
        Request reques = new Request.Builder()
                .url("http://169.254.217.5:8080/bullking1/login")
                .post(body)
                .build();
        Call call = okhttp.newCall(reques);
        //调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
              Log.d("eeeeee","onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               String s= response.body().string();
                try {
                    final JSONObject obj = new JSONObject(s);
                    final String dataStr = obj.getString("dataStr");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (dataStr.equals("login succeed")){
                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                int uid = 0;
                                try {
                                    uid = obj.getInt("id");

                                    SharedPreferences.Editor edit = pre.edit();
                                    edit.putInt("uid",uid);
                                    edit.putString("uname",name);
                                    edit.commit();
                                    Log.d("dddddd",uid+"");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }else{
                                    Toast.makeText(LoginActivity.this, "没有这个用户,请注册", Toast.LENGTH_SHORT).show();
                                }
                           }
                        });


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

    }



}
