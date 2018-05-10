package com.yxld.yxchuangxin.entity.camera;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * 作者：Android on 2017/10/18
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class Shared extends BaseEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * fxIfShebei : -1
         * fxShebeiId : 66
         * fxShijian : 2017-10-17 10:55:02.0
         * fxYezhuId : 6
         * id : 38
         */

        private int fxIfShebei;
        private int fxShebeiId;
        private String fxShijian;
        private int fxYezhuId;
        private int id;

        public int getFxIfShebei() {
            return fxIfShebei;
        }

        public void setFxIfShebei(int fxIfShebei) {
            this.fxIfShebei = fxIfShebei;
        }

        public int getFxShebeiId() {
            return fxShebeiId;
        }

        public void setFxShebeiId(int fxShebeiId) {
            this.fxShebeiId = fxShebeiId;
        }

        public String getFxShijian() {
            return fxShijian;
        }

        public void setFxShijian(String fxShijian) {
            this.fxShijian = fxShijian;
        }

        public int getFxYezhuId() {
            return fxYezhuId;
        }

        public void setFxYezhuId(int fxYezhuId) {
            this.fxYezhuId = fxYezhuId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
