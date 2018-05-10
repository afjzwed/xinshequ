package com.yxld.yxchuangxin.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：hu on 2017/6/29
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class Accredit {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * applyTime : 1483113600000
         * id : 20161231100414
         * loupanid : 中远公馆
         * name : 小兰
         * num : 1
         * ptime : 1483113600000
         * status : 1
         */

        private long applyTime;
        private String id;
        private String loupanid;
        private String name;
        private String num;
        private String ptime;
        private int status;

        public long getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(long applyTime) {
            this.applyTime = applyTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLoupanid() {
            return loupanid;
        }

        public void setLoupanid(String loupanid) {
            this.loupanid = loupanid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
