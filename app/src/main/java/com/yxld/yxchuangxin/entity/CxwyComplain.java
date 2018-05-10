package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * 作者：hu on 2017/6/26
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class CxwyComplain extends BaseEntity {

    /**
     * list : [{"tousuFankuiyijian":"","tousuNeirong":"哈哈哈哈哈哈哈","tousuStatus":"待审核","tousuTest2":"","tousuTime":"2017-06-26 17:56:55","tousuType":"2","tousuXmsolutiontime":""},{"tousuFankuiyijian":"","tousuNeirong":"哈哈哈哈哈哈哈","tousuStatus":"待审核","tousuTest2":"","tousuTime":"2017-06-26 17:38:25","tousuType":"2","tousuXmsolutiontime":""},{"tousuFankuiyijian":"","tousuNeirong":"。。。。。。","tousuStatus":"待审核","tousuTest2":"","tousuTime":"2017-06-26 17:32:55","tousuType":"1","tousuXmsolutiontime":""},{"tousuFankuiyijian":"","tousuNeirong":"","tousuStatus":"待审核","tousuTest2":"","tousuTime":"2017-06-26 17:30:59","tousuType":"1","tousuXmsolutiontime":""},{"tousuFankuiyijian":"","tousuNeirong":"","tousuStatus":"待审核","tousuTest2":"","tousuTime":"2017-06-26 17:24:25","tousuType":"1","tousuXmsolutiontime":""},{"tousuFankuiyijian":"","tousuNeirong":"管理不好","tousuStatus":"待审核","tousuTest2":"","tousuTime":"2017-06-26 17:23:40","tousuType":"1","tousuXmsolutiontime":""}]
     * success : true
     */

    private boolean success;
    private List<ListBean> list;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * tousuFankuiyijian :
         * tousuNeirong : 哈哈哈哈哈哈哈
         * tousuStatus : 待审核
         * tousuTest2 :
         * tousuTime : 2017-06-26 17:56:55
         * tousuType : 2
         * tousuXmsolutiontime :
         */

        private String tousuFankuiyijian;
        private String tousuNeirong;
        private String tousuStatus;
        private String tousuTest2;
        private String tousuTime;
        private String tousuType;
        private String tousuXmsolutiontime;

        public String getTousuFankuiyijian() {
            return tousuFankuiyijian;
        }

        public void setTousuFankuiyijian(String tousuFankuiyijian) {
            this.tousuFankuiyijian = tousuFankuiyijian;
        }

        public String getTousuNeirong() {
            return tousuNeirong;
        }

        public void setTousuNeirong(String tousuNeirong) {
            this.tousuNeirong = tousuNeirong;
        }

        public String getTousuStatus() {
            return tousuStatus;
        }

        public void setTousuStatus(String tousuStatus) {
            this.tousuStatus = tousuStatus;
        }

        public String getTousuTest2() {
            return tousuTest2;
        }

        public void setTousuTest2(String tousuTest2) {
            this.tousuTest2 = tousuTest2;
        }

        public String getTousuTime() {
            return tousuTime;
        }

        public void setTousuTime(String tousuTime) {
            this.tousuTime = tousuTime;
        }

        public String getTousuType() {
            return tousuType;
        }

        public void setTousuType(String tousuType) {
            this.tousuType = tousuType;
        }

        public String getTousuXmsolutiontime() {
            return tousuXmsolutiontime;
        }

        public void setTousuXmsolutiontime(String tousuXmsolutiontime) {
            this.tousuXmsolutiontime = tousuXmsolutiontime;
        }
    }
}
