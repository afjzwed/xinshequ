package com.yxld.yxchuangxin.entity.goods;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.ArrayList;

/**
 * 作者：Android on 2017/10/25
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class ShopCart extends BaseEntity {

    /**
     * success : true
     * rows : [{"cartSpmingcheng":"1","cartSpbianhao":908,"cartSpguige":"1","cartSpdanjia":1,"cartSpzhutu":"","cartNum":6,"cartAdminId":2218,"cartIsKucun":1,"cartIsShangjia":1,"cartIsDajian":1,"cartIsYejian":1}]
     * total : 1
     */

    private boolean success;
    private int total;
    private ArrayList<ShapCartBean> rows;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<ShapCartBean> getRows() {
        return rows;
    }

    public void setRows(ArrayList<ShapCartBean> rows) {
        this.rows = rows;
    }

    public static class ShapCartBean {
        /**
         * cartSpmingcheng : 1
         * cartSpbianhao : 908
         * cartSpguige : 1
         * cartSpdanjia : 1
         * cartSpzhutu :
         * cartNum : 6
         * cartAdminId : 2218
         * cartIsKucun : 1
         * cartIsShangjia : 1
         * cartIsDajian : 1
         * cartIsYejian : 1
         */

        private String cartSpmingcheng;
        private int cartSpbianhao;
        private String cartSpguige;
        private double cartSpdanjia;
        private String cartSpzhutu;
        private int cartNum;
        private int cartAdminId;
        private int cartIsKucun;
        private int cartIsShangjia;
        private int cartIsDajian;
        private int cartIsYejian;
        private boolean checked;

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public String getCartSpmingcheng() {
            return cartSpmingcheng;
        }

        public void setCartSpmingcheng(String cartSpmingcheng) {
            this.cartSpmingcheng = cartSpmingcheng;
        }

        public int getCartSpbianhao() {
            return cartSpbianhao;
        }

        public void setCartSpbianhao(int cartSpbianhao) {
            this.cartSpbianhao = cartSpbianhao;
        }

        public String getCartSpguige() {
            return cartSpguige;
        }

        public void setCartSpguige(String cartSpguige) {
            this.cartSpguige = cartSpguige;
        }

        public double getCartSpdanjia() {
            return cartSpdanjia;
        }

        public void setCartSpdanjia(double cartSpdanjia) {
            this.cartSpdanjia = cartSpdanjia;
        }

        public String getCartSpzhutu() {
            return cartSpzhutu;
        }

        public void setCartSpzhutu(String cartSpzhutu) {
            this.cartSpzhutu = cartSpzhutu;
        }

        public int getCartNum() {
            return cartNum;
        }

        public void setCartNum(int cartNum) {
            this.cartNum = cartNum;
        }

        public int getCartAdminId() {
            return cartAdminId;
        }

        public void setCartAdminId(int cartAdminId) {
            this.cartAdminId = cartAdminId;
        }

        public int getCartIsKucun() {
            return cartIsKucun;
        }

        public void setCartIsKucun(int cartIsKucun) {
            this.cartIsKucun = cartIsKucun;
        }

        public int getCartIsShangjia() {
            return cartIsShangjia;
        }

        public void setCartIsShangjia(int cartIsShangjia) {
            this.cartIsShangjia = cartIsShangjia;
        }

        public int getCartIsDajian() {
            return cartIsDajian;
        }

        public void setCartIsDajian(int cartIsDajian) {
            this.cartIsDajian = cartIsDajian;
        }

        public int getCartIsYejian() {
            return cartIsYejian;
        }

        public void setCartIsYejian(int cartIsYejian) {
            this.cartIsYejian = cartIsYejian;
        }
    }
}
