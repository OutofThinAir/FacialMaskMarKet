package com.huamei.facialmaskmarket.bean;

import java.util.List;

/**
 * Use:
 * Author:陈懿鹏
 * Data:2017/4/21.
 */

public class ShopCarBean {

    /**
     * cartItemList : [{"colorID":0,"count":2,"id":76,"name":"御泥坊精华水分光感气垫BB霜","pic":"http://image.hmeili.com/yunifang/images/goods/1280/goods_img/16120518307098199689667991.jpg","price":139,"productID":1280,"repertory":899,"sizeID":0,"userID":44},{"colorID":0,"count":2,"id":77,"name":"热销新品丨嫩肌酵素黑膜礼盒21片","pic":"https://image.yunifang.com/yunifang/images/goods/1638/goods_img/17030210211762506087062132.jpg","price":139.9,"productID":1638,"repertory":899,"sizeID":0,"userID":44}]
     * response : cart
     */

    private String response;
    private List<CartItemListBean> cartItemList;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<CartItemListBean> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItemListBean> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public static class CartItemListBean {
        /**
         * colorID : 0
         * count : 2
         * id : 76
         * name : 御泥坊精华水分光感气垫BB霜
         * pic : http://image.hmeili.com/yunifang/images/goods/1280/goods_img/16120518307098199689667991.jpg
         * price : 139
         * productID : 1280
         * repertory : 899
         * sizeID : 0
         * userID : 44
         */

        private int colorID;
        private int count;
        private int id;
        private String name;
        private String pic;
        private int price;
        private int productID;
        private int repertory;
        private int sizeID;
        private int userID;

        public int getColorID() {
            return colorID;
        }

        public void setColorID(int colorID) {
            this.colorID = colorID;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getProductID() {
            return productID;
        }

        public void setProductID(int productID) {
            this.productID = productID;
        }

        public int getRepertory() {
            return repertory;
        }

        public void setRepertory(int repertory) {
            this.repertory = repertory;
        }

        public int getSizeID() {
            return sizeID;
        }

        public void setSizeID(int sizeID) {
            this.sizeID = sizeID;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }
    }
}
