package com.huamei.facialmaskmarket.bean;

import java.util.ArrayList;

/**
 * Use:
 * Author:陈懿鹏
 * Data:2017/4/21.
 */

public class GoodsMinuteBean {
    private ArrayList<String> goods_desc;
    private String goods_img;
    private String goods_name;
    private double shop_price;

    public GoodsMinuteBean(ArrayList<String> goods_desc, String goods_img, String goods_name, double shop_price) {
        this.goods_desc = goods_desc;
        this.goods_img = goods_img;
        this.goods_name = goods_name;
        this.shop_price = shop_price;
    }

    public ArrayList<String> getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(ArrayList<String> goods_desc) {
        this.goods_desc = goods_desc;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public double getShop_price() {
        return shop_price;
    }

    public void setShop_price(double shop_price) {
        this.shop_price = shop_price;
    }
}
