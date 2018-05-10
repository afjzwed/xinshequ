package com.yxld.yxchuangxin.entity;

import java.util.List;

/**
 * 作者：hu on 2017/7/6
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class CarJiaofeiRecord {

    /**
     * success : true
     * msg : 返回成功
     * data : [{"transCode":"cx0004","serialNo":"1494315638107","parkNo":"483841","mediaNo":"湘A6M93Z","money":150,"month":1,"time":"2017-05-09 15:40:38.0","isnew":1,"result":"00","desc":"成功","rechargeType":"xj","rechargeSerialNum":null,"rechargeResult":null,"jfcwReceiptStr":"00010321cw000003","id":32},{"transCode":"cx0004","serialNo":"1494325406303","parkNo":"483841","mediaNo":"湘A6M93Z","money":150,"month":1,"time":"2017-05-09 18:23:26.0","isnew":1,"result":"00","desc":"成功","rechargeType":"xj","rechargeSerialNum":null,"rechargeResult":null,"jfcwReceiptStr":"00010321cw000003","id":33},{"transCode":"cx0004","serialNo":"1494325494721","parkNo":"483841","mediaNo":"湘A6M93Z","money":150,"month":1,"time":"2017-05-09 18:24:55.0","isnew":1,"result":"00","desc":"成功","rechargeType":"xj","rechargeSerialNum":null,"rechargeResult":null,"jfcwReceiptStr":"00010321cw000003","id":34}]
     */

    private boolean success;
    private String msg;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * transCode : cx0004
         * serialNo : 1494315638107
         * parkNo : 483841
         * mediaNo : 湘A6M93Z
         * money : 150
         * month : 1
         * time : 2017-05-09 15:40:38.0
         * isnew : 1
         * result : 00
         * desc : 成功
         * rechargeType : xj
         * rechargeSerialNum : null
         * rechargeResult : null
         * jfcwReceiptStr : 00010321cw000003
         * id : 32
         */

        private String transCode;
        private String serialNo;
        private String parkNo;
        private String mediaNo;
        private double payMoney;
        private int payMonth;
        private String payTime;
        private String carTime;
        private String carTerm;
        private int isnew;
        private String payResult;
        private String payDesc;
        private String rechargeType;
        private Object rechargeSerialNum;
        private Object rechargeResult;
        private String jfcwReceiptStr;
        private int id;

        public String getTransCode() {
            return transCode;
        }

        public void setTransCode(String transCode) {
            this.transCode = transCode;
        }

        public String getSerialNo() {
            return serialNo;
        }

        public void setSerialNo(String serialNo) {
            this.serialNo = serialNo;
        }

        public String getParkNo() {
            return parkNo;
        }

        public void setParkNo(String parkNo) {
            this.parkNo = parkNo;
        }

        public String getMediaNo() {
            return mediaNo;
        }

        public void setMediaNo(String mediaNo) {
            this.mediaNo = mediaNo;
        }

        public double getPayMoney() {
            return payMoney;
        }

        public void setPayMoney(double payMoney) {
            this.payMoney = payMoney;
        }

        public int getPayMonth() {
            return payMonth;
        }

        public void setPayMonth(int payMonth) {
            this.payMonth = payMonth;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public int getIsnew() {
            return isnew;
        }

        public void setIsnew(int isnew) {
            this.isnew = isnew;
        }


        public String getPayResult() {
            return payResult;
        }

        public void setPayResult(String payResult) {
            this.payResult = payResult;
        }


        public String getPayDesc() {
            return payDesc;
        }

        public void setPayDesc(String payDesc) {
            this.payDesc = payDesc;
        }

        public String getRechargeType() {
            return rechargeType;
        }

        public void setRechargeType(String rechargeType) {
            this.rechargeType = rechargeType;
        }

        public Object getRechargeSerialNum() {
            return rechargeSerialNum;
        }

        public void setRechargeSerialNum(Object rechargeSerialNum) {
            this.rechargeSerialNum = rechargeSerialNum;
        }

        public Object getRechargeResult() {
            return rechargeResult;
        }

        public void setRechargeResult(Object rechargeResult) {
            this.rechargeResult = rechargeResult;
        }

        public String getJfcwReceiptStr() {
            return jfcwReceiptStr;
        }

        public void setJfcwReceiptStr(String jfcwReceiptStr) {
            this.jfcwReceiptStr = jfcwReceiptStr;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
