package com.huamei.facialmaskmarket.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.huamei.facialmaskmarket.R;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Use:
 * Author:陈懿鹏
 * Data:2017/4/21.
 */

public class RegisterActivity extends Activity implements View.OnClickListener{
    private EditText into_uanme;
    private EditText into_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_pager_layout);
        initView();

    }

    //初始化控件
    private void initView(){
        ImageView last_pager= (ImageView) findViewById(R.id.register_pager_last);

        into_uanme = (EditText) findViewById(R.id.register_pager_into_uanme);
        into_pwd = (EditText) findViewById(R.id.register_pager_into_upwd);
        Button register= (Button) findViewById(R.id.register_pager_login_bu);
        last_pager.setOnClickListener(this);

        register.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_pager_last:
                break;

            case R.id.register_pager_login_bu:
               // Toast.makeText(this, "zhuc", Toast.LENGTH_SHORT).show();
                if (!TextUtils.isEmpty(into_uanme.getText().toString())&&!TextUtils.isEmpty(into_pwd.getText().toString())){
                   registerGet(into_uanme.getText().toString(),into_pwd.getText().toString());
                }else{
                    Toast.makeText(this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
                }

                break;

        }

    }

    //注册,请求后台注册接口
    private void registerGet(String name,String pwd){
        String url="http://169.254.217.5:8080/bullking1/register?name="+name+"&pwd="+pwd;

        com.squareup.okhttp.OkHttpClient okHttpClient = new com.squareup.okhttp.OkHttpClient();
        com.squareup.okhttp.Request requse = new com.squareup.okhttp.Request.Builder()
                .url(url)
                .get()
                .build();
        com.squareup.okhttp.Call call=okHttpClient.newCall(requse);
        call.enqueue(new com.squareup.okhttp.Callback() {
            @Override
            public void onFailure(com.squareup.okhttp.Request request, IOException e) {

            }

            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {
                String s=response.body().string();
                try {
                    JSONObject obj = new JSONObject(s);
                    final String dataStr = obj.getString("dataStr");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (dataStr.equals("register succeed")){
                                //注册成功
                                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } catch (Exception e) {

                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });

            }
        });



    }
}
