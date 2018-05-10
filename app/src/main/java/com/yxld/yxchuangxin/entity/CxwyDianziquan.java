package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

public class CxwyDianziquan extends BaseEntity {

    /**
     * rows : [{"batchId":56,"batchRemark":"","denomination":233,"doneeId":2218,"doneeName":"胡智鹏","doneePhone":"18670819116","fangDong":"","givenId":136,"givenName":"刘诗中远","givenReason":"123","id":427,"payId":0,"payTotal":0,"status":0,"type":0,"voucherType":3,"xiangmuId":346,"xiangmuName":"中远公馆"},{"batchId":57,"batchRemark":"","denomination":123,"doneeId":2218,"doneeName":"胡智鹏","doneePhone":"18670819116","fangDong":"","givenId":136,"givenName":"刘诗中远","givenReason":"111","id":818,"payId":0,"payTotal":0,"status":0,"type":0,"voucherType":3,"xiangmuId":346,"xiangmuName":"中远公馆"},{"batchId":62,"batchRemark":"","denomination":10,"doneeId":2218,"doneeName":"胡智鹏","doneePhone":"18670819116","fangDong":"","givenId":136,"givenName":"刘诗中远","givenReason":"批量赠送4个人测试","givenTime":"2017-08-30 10:14:08","id":1203,"payId":0,"payTotal":0,"status":0,"type":0,"voucherType":3,"xiangmuId":346,"xiangmuName":"中远公馆"}]
     * status : 0
     * total : 10
     */

    private int total;
    private List<RowsBean> list;
    private double balance;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getList() {
        return list;
    }

    public void setList(List<RowsBean> list) {
        this.list = list;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static class RowsBean {
        private String batchBeginTime;
        private String batchEndTime;
        private int batchId;
        private String batchRemark;
        private int denomination;
        private int doneeId;
        private String doneeName;
        private String doneePhone;
        private String fangDong;
        private int givenId;
        private String givenName;
        private String givenReason;
        private String givenTime;
        private int id;
        private String payBegin;
        private String payDate;
        private String payEnd;
        private int payId;
        private double payTotal;
        private int status;
        private int type;
        private int voucherType;
        private int xiangmuId;
        private String xiangmuName;
        private String orderNum;

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public String getBatchBeginTime() {
            return batchBeginTime;
        }

        public void setBatchBeginTime(String batchBeginTime) {
            this.batchBeginTime = batchBeginTime;
        }

        public String getBatchEndTime() {
            return batchEndTime;
        }

        public void setBatchEndTime(String batchEndTime) {
            this.batchEndTime = batchEndTime;
        }

        public int getBatchId() {
            return batchId;
        }

        public void setBatchId(int batchId) {
            this.batchId = batchId;
        }

        public String getBatchRemark() {
            return batchRemark;
        }

        public void setBatchRemark(String batchRemark) {
            this.batchRemark = batchRemark;
        }

        public int getDenomination() {
            return denomination;
        }

        public void setDenomination(int denomination) {
            this.denomination = denomination;
        }

        public int getDoneeId() {
            return doneeId;
        }

        public void setDoneeId(int doneeId) {
            this.doneeId = doneeId;
        }

        public String getDoneeName() {
            return doneeName;
        }

        public void setDoneeName(String doneeName) {
            this.doneeName = doneeName;
        }

        public String getDoneePhone() {
            return doneePhone;
        }

        public void setDoneePhone(String doneePhone) {
            this.doneePhone = doneePhone;
        }

        public String getFangDong() {
            return fangDong;
        }

        public void setFangDong(String fangDong) {
            this.fangDong = fangDong;
        }

        public int getGivenId() {
            return givenId;
        }

        public void setGivenId(int givenId) {
            this.givenId = givenId;
        }

        public String getGivenName() {
            return givenName;
        }

        public void setGivenName(String givenName) {
            this.givenName = givenName;
        }

        public String getGivenReason() {
            return givenReason;
        }

        public void setGivenReason(String givenReason) {
            this.givenReason = givenReason;
        }

        public String getGivenTime() {
            return givenTime;
        }

        public void setGivenTime(String givenTime) {
            this.givenTime = givenTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPayBegin() {
            return payBegin;
        }

        public void setPayBegin(String payBegin) {
            this.payBegin = payBegin;
        }

        public String getPayDate() {
            return payDate;
        }

        public void setPayDate(String payDate) {
            this.payDate = payDate;
        }

        public String getPayEnd() {
            return payEnd;
        }

        public void setPayEnd(String payEnd) {
            this.payEnd = payEnd;
        }

        public int getPayId() {
            return payId;
        }

        public void setPayId(int payId) {
            this.payId = payId;
        }

        public double getPayTotal() {
            return payTotal;
        }

        public void setPayTotal(double payTotal) {
            this.payTotal = payTotal;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getVoucherType() {
            return voucherType;
        }

        public void setVoucherType(int voucherType) {
            this.voucherType = voucherType;
        }

        public int getXiangmuId() {
            return xiangmuId;
        }

        public void setXiangmuId(int xiangmuId) {
            this.xiangmuId = xiangmuId;
        }

        public String getXiangmuName() {
            return xiangmuName;
        }

        public void setXiangmuName(String xiangmuName) {
            this.xiangmuName = xiangmuName;
        }
    }
}
