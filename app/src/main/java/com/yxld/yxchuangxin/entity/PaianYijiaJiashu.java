package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * 作者：Android on 2017/9/20
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class PaianYijiaJiashu extends BaseEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * fxHaoma : 13875231886
         * fxIfZhuji : -1
         * fxShijian : 2017-09-20 22:05:37.0
         * fxZhujiMac : 573932373138
         * id : 7
         */

        private String fxHaoma;
        private String fxIfZhuji;
        private String fxShijian;
        private String fxZhujiMac;
        private int id;

        public String getFxHaoma() {
            return fxHaoma;
        }

        public void setFxHaoma(String fxHaoma) {
            this.fxHaoma = fxHaoma;
        }

        public String getFxIfZhuji() {
            return fxIfZhuji;
        }

        public void setFxIfZhuji(String fxIfZhuji) {
            this.fxIfZhuji = fxIfZhuji;
        }

        public String getFxShijian() {
            return fxShijian;
        }

        public void setFxShijian(String fxShijian) {
            this.fxShijian = fxShijian;
        }

        public String getFxZhujiMac() {
            return fxZhujiMac;
        }

        public void setFxZhujiMac(String fxZhujiMac) {
            this.fxZhujiMac = fxZhujiMac;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
