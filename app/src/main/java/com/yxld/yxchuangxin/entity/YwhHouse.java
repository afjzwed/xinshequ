package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/11/13.
 */

public class YwhHouse extends BaseEntity {

    private List<DataBean> data;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 3557
         * building : 888
         * unit : 888
         * period : 1
         * roomNo : 888
         * deedImage :
         * paperWork : null
         * projectId : 346
         * area : 100
         * propertyNumber :
         * fwHuxing : sanshi
         * fwJiaofangTime : 2017-09-20 11:27:10.0
         * fwIsChuzu : 0
         * fwstate : 1
         * fwzx : null
         * fwsf : null
         */
        private int id;
        private String building;
        private String unit;
        private String period;
        private String roomNo;
        private String deedImage;
        private String paperWork;
        private int projectId;
        private double area;
        private String propertyNumber;
        private String fwHuxing;
        private String fwJiaofangTime;
        private int fwIsChuzu;
        private int fwstate;
        private Object fwzx;
        private Object fwsf;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBuilding() {
            return building;
        }

        public void setBuilding(String building) {
            this.building = building;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getRoomNo() {
            return roomNo;
        }

        public void setRoomNo(String roomNo) {
            this.roomNo = roomNo;
        }

        public String getDeedImage() {
            return deedImage;
        }

        public void setDeedImage(String deedImage) {
            this.deedImage = deedImage;
        }

        public String getPaperWork() {
            return paperWork;
        }

        public void setPaperWork(String paperWork) {
            this.paperWork = paperWork;
        }

        public int getProjectId() {
            return projectId;
        }

        public void setProjectId(int projectId) {
            this.projectId = projectId;
        }

        public double getArea() {
            return area;
        }

        public void setArea(double area) {
            this.area = area;
        }

        public String getPropertyNumber() {
            return propertyNumber;
        }

        public void setPropertyNumber(String propertyNumber) {
            this.propertyNumber = propertyNumber;
        }

        public String getFwHuxing() {
            return fwHuxing;
        }

        public void setFwHuxing(String fwHuxing) {
            this.fwHuxing = fwHuxing;
        }

        public String getFwJiaofangTime() {
            return fwJiaofangTime;
        }

        public void setFwJiaofangTime(String fwJiaofangTime) {
            this.fwJiaofangTime = fwJiaofangTime;
        }

        public int getFwIsChuzu() {
            return fwIsChuzu;
        }

        public void setFwIsChuzu(int fwIsChuzu) {
            this.fwIsChuzu = fwIsChuzu;
        }

        public int getFwstate() {
            return fwstate;
        }

        public void setFwstate(int fwstate) {
            this.fwstate = fwstate;
        }

        public Object getFwzx() {
            return fwzx;
        }

        public void setFwzx(Object fwzx) {
            this.fwzx = fwzx;
        }

        public Object getFwsf() {
            return fwsf;
        }

        public void setFwsf(Object fwsf) {
            this.fwsf = fwsf;
        }
    }
}
