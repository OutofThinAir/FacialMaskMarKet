package com.huamei.facialmaskmarket.bean;

import java.util.List;

/**
 * Use:
 * Author:陈懿鹏
 * Data:2017/4/12.
 */

public class PicBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pic : http://www.jiazhoubadanmu.com/sites/all/themes/almd_theme/images/front-1.jpg
         */

        private String pic;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "pic='" + pic + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PicBean{" +
                "data=" + data +
                '}';
    }
}
