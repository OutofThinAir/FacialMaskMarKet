package com.huamei.facialmaskmarket.bean;

/**
 * Use:
 * Author:陈懿鹏
 * Data:2017/4/12.
 */

public class MyPagerBean {
    private String title;
    private int picId;

    public MyPagerBean(String title, int picId) {
        this.title = title;
        this.picId = picId;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }
}
