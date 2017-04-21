package com.huamei.facialmaskmarket.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huamei.facialmaskmarket.R;
import com.huamei.facialmaskmarket.bean.GoodsMinuteBean;
import com.huamei.facialmaskmarket.bean.PicBean;
import com.huamei.facialmaskmarket.util.MyImageLoader;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Use:
 * Author:陈懿鹏
 * Data:2017/4/20.
 */

public class MinuteActivity extends Activity implements View.OnClickListener{

    private ViewPager pager;
    private TextView title;
    private TextView price;
    private Button addShopp;
    private Button lijgoumai;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minute_pager_layout);
        initView();
        Intent intent =getIntent();
        id = intent.getStringExtra("id");
        String url="http://m.yunifang.com/yunifang/mobile/goods/detail?" +
                "random=46389&encode=70ed2ed2facd7a812ef46717b37148d6&id="+ id;
        Log.d("Minute------->",url);
        okHttpRequse(url);

    }
    //初始化话控件
    private void initView(){
        pager = (ViewPager) findViewById(R.id.minute_pager_vp);
        title = (TextView) findViewById(R.id.minute_pager_title);
        price = (TextView) findViewById(R.id.minute_pager_price);
        addShopp = (Button) findViewById(R.id.minute_pager_add_shoppcar);
        lijgoumai = (Button) findViewById(R.id.minute_pager_buy);


    }

    //请求网络
    private void okHttpRequse(final String url){
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
                try {
                    JSONObject obj = new JSONObject(s);
                    JSONObject data = obj.getJSONObject("data");
                    JSONObject goods = data.getJSONObject("goods");
                    String goods_desc = goods.getString("goods_desc");
                    JSONArray pics = new JSONArray(goods_desc);
                    final ArrayList<String> picurl = new ArrayList<String>();
                    for (int i = 0; i <pics.length() ; i++) {
                        JSONObject jsonObject = pics.getJSONObject(i);
                        picurl.add(jsonObject.getString("url"));

                    }
                    final String title1 =goods.getString("goods_name");
                    final String goods_img =goods.getString("goods_img");
                    final double price1=goods.getDouble("shop_price");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            title.setText(title1);
                            price.setText("￥"+price1);
                            //图片的集合
                            ArrayList<ImageView> images = new ArrayList<ImageView>();
                            for (int i = 0; i <picurl.size() ; i++) {
                                ImageView image=new ImageView(MinuteActivity.this);
                                Glide.with(MinuteActivity.this).load(picurl.get(i)).into(image);
                                image.setScaleType(ImageView.ScaleType.FIT_XY);

                                images.add(image);

                            }

                            pager.setAdapter(new Myadapter(images));
                            //添加购物车
                            addShopp.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SharedPreferences shr =getSharedPreferences("cofig",MODE_PRIVATE);
                                    int uid = shr.getInt("uid", -1);
                                    if (uid!=-1){
                                        //添加购物车
                                        addShopCar(""+id,"2",""+price1,title1,""+uid,goods_img);
                                    }else {
                                        Toast.makeText(MinuteActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    private class Myadapter extends PagerAdapter{
        private ArrayList<ImageView> list;

        public Myadapter(ArrayList<ImageView> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           container.removeView(list.get(position));
        }
    }

    //添加购物车
    private void addShopCar(String productID,String count,String price,String name,String userID,
    String pic){


            okhttp3.OkHttpClient okhttp = new okhttp3.OkHttpClient();
            //请求体
            RequestBody body =  new FormBody.Builder()
                    .add("productID",productID)
                    .add("count",count)
                    .add("price",price)
                    .add("name",name)
                    .add("userID",userID)
                    .add("pic",pic)
                    .build();
            okhttp3.Request reques = new okhttp3.Request.Builder()
                    .url("http://169.254.217.5:8080/bullking1/cart")
                    .post(body)
                    .build();
            okhttp3.Call call = okhttp.newCall(reques);
            //调度
            call.enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {
                    Log.d("eeeeee","onFailure");
                }

                @Override
                public void onResponse(okhttp3.Call call, Response response) throws IOException {
                    String s= response.body().string();
                    try {
                        JSONObject obj =new JSONObject(s);
                        final String dataStr = obj.getString("dataStr");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (dataStr.equals("succeed")){
                                    Toast.makeText(MinuteActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
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
