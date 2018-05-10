package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * 作者：hu on 2017/6/28
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class RoomRent extends BaseEntity {

    /**
     * msg : 查找成功
     * success : true
     * yezhu : [{"fwDanyuan":"1","fwFanghao":"202","fwHuxing":"三室两厅","fwId":133,"fwIsChuzu":1,"fwLoudong":"1","fwLoupanId":321,"fwZhuangtai":2,"fwyzRztime":"2017-05-25 00:00:00","jfFwTypeLeixing":"居家电梯房","xmName":"凯姆国际","yezhu_name":"胡智鹏","yezhu_shouji":"18670819116"}]
     */

    private boolean success;
    private List<YezhuBean> yezhu;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<YezhuBean> getYezhu() {
        return yezhu;
    }

    public void setYezhu(List<YezhuBean> yezhu) {
        this.yezhu = yezhu;
    }

    public static class YezhuBean {
        /**
         * fwDanyuan : 1
         * fwFanghao : 202
         * fwHuxing : 三室两厅
         * fwId : 133
         * fwIsChuzu : 1
         * fwLoudong : 1
         * fwLoupanId : 321
         * fwZhuangtai : 2
         * fwyzRztime : 2017-05-25 00:00:00
         * jfFwTypeLeixing : 居家电梯房
         * xmName : 凯姆国际
         * yezhu_name : 胡智鹏
         * yezhu_shouji : 18670819116
         */

        private String fwDanyuan;
        private String fwFanghao;
        private String fwHuxing;
        private int fwId;
        private int fwIsChuzu;
        private String fwLoudong;
        private int fwLoupanId;
        private int fwZhuangtai;
        private String fwyzRztime;
        private String jfFwTypeLeixing;
        private String xmName;
        private String yezhu_name;
        private String yezhu_shouji;

        public String getFwDanyuan() {
            return fwDanyuan;
        }

        public void setFwDanyuan(String fwDanyuan) {
            this.fwDanyuan = fwDanyuan;
        }

        public String getFwFanghao() {
            return fwFanghao;
        }

        public void setFwFanghao(String fwFanghao) {
            this.fwFanghao = fwFanghao;
        }

        public String getFwHuxing() {
            return fwHuxing;
        }

        public void setFwHuxing(String fwHuxing) {
            this.fwHuxing = fwHuxing;
        }

        public int getFwId() {
            return fwId;
        }

        public void setFwId(int fwId) {
            this.fwId = fwId;
        }

        public int getFwIsChuzu() {
            return fwIsChuzu;
        }

        public void setFwIsChuzu(int fwIsChuzu) {
            this.fwIsChuzu = fwIsChuzu;
        }

        public String getFwLoudong() {
            return fwLoudong;
        }

        public void setFwLoudong(String fwLoudong) {
            this.fwLoudong = fwLoudong;
        }

        public int getFwLoupanId() {
            return fwLoupanId;
        }

        public void setFwLoupanId(int fwLoupanId) {
            this.fwLoupanId = fwLoupanId;
        }

        public int getFwZhuangtai() {
            return fwZhuangtai;
        }

        public void setFwZhuangtai(int fwZhuangtai) {
            this.fwZhuangtai = fwZhuangtai;
        }

        public String getFwyzRztime() {
            return fwyzRztime;
        }

        public void setFwyzRztime(String fwyzRztime) {
            this.fwyzRztime = fwyzRztime;
        }

        public String getJfFwTypeLeixing() {
            return jfFwTypeLeixing;
        }

        public void setJfFwTypeLeixing(String jfFwTypeLeixing) {
            this.jfFwTypeLeixing = jfFwTypeLeixing;
        }

        public String getXmName() {
            return xmName;
        }

        public void setXmName(String xmName) {
            this.xmName = xmName;
        }

        public String getYezhu_name() {
            return yezhu_name;
        }

        public void setYezhu_name(String yezhu_name) {
            this.yezhu_name = yezhu_name;
        }

        public String getYezhu_shouji() {
            return yezhu_shouji;
        }

        public void setYezhu_shouji(String yezhu_shouji) {
            this.yezhu_shouji = yezhu_shouji;
        }
    }
}
