package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * Created by hu on 2017/5/6.
 */

public class CxwyClassifyInfo extends BaseEntity {

    /**
     * data : [{"classifyId":4,"classifyName":"牛奶","classifyHigherId":3,"classifyLevel":2,"classifyDisplay":1,"classifyBusinessNumber":"1001"},{"classifyId":8,"classifyName":"咖啡","classifyHigherId":3,"classifyLevel":2,"classifyDisplay":1,"classifyBusinessNumber":"1001"}]
     * msg : 查询成功成功
     * success : 1
     * total : 0
     */

    private String success;
    private int total;
    private List<DataBean> data;



    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * classifyId : 4
         * classifyName : 牛奶
         * classifyHigherId : 3
         * classifyLevel : 2
         * classifyDisplay : 1
         * classifyBusinessNumber : 1001
         */

        private int classifyId;
        private String classifyName;
        private int classifyHigherId;
        private int classifyLevel;
        private int classifyDisplay;
        private String classifyBusinessNumber;

        public int getClassifyId() {
            return classifyId;
        }

        public void setClassifyId(int classifyId) {
            this.classifyId = classifyId;
        }

        public String getClassifyName() {
            return classifyName;
        }

        public void setClassifyName(String classifyName) {
            this.classifyName = classifyName;
        }

        public int getClassifyHigherId() {
            return classifyHigherId;
        }

        public void setClassifyHigherId(int classifyHigherId) {
            this.classifyHigherId = classifyHigherId;
        }

        public int getClassifyLevel() {
            return classifyLevel;
        }

        public void setClassifyLevel(int classifyLevel) {
            this.classifyLevel = classifyLevel;
        }

        public int getClassifyDisplay() {
            return classifyDisplay;
        }

        public void setClassifyDisplay(int classifyDisplay) {
            this.classifyDisplay = classifyDisplay;
        }

        public String getClassifyBusinessNumber() {
            return classifyBusinessNumber;
        }

        public void setClassifyBusinessNumber(String classifyBusinessNumber) {
            this.classifyBusinessNumber = classifyBusinessNumber;
        }
    }
}
