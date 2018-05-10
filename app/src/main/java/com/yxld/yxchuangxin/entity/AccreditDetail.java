package com.yxld.yxchuangxin.entity;

import java.util.List;

/**
 * 作者：hu on 2017/6/30
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class AccreditDetail {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * goodsDesc : bugg
         * goodsName : nkni
         * goodsNum : 1
         */

        private String goodsDesc;
        private String goodsName;
        private int goodsNum;

        public String getGoodsDesc() {
            return goodsDesc;
        }

        public void setGoodsDesc(String goodsDesc) {
            this.goodsDesc = goodsDesc;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }
    }
}
