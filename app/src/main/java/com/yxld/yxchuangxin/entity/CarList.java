package com.yxld.yxchuangxin.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：hu on 2017/7/6
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class CarList {

    /**
     * success : true
     * msg : 查找完成
     * data : [{"parkWeizhi":"小区外坪","parkFee":150,"clId":1646,"clYezhuId":2185,"clmediaNo":"湘A6M93Z","clparkNo":"483841","clplNo":"非固定车位","clmonthType":1,"cardTimr":1498463121000,"protocolTime":1514649600000,"scTime":null,"ckId":null,"mediaNo":null,"jsTime":null,"serialNo":null,"type":1},{"parkWeizhi":"小区外坪","parkFee":150,"clId":1645,"clYezhuId":2185,"clmediaNo":"湘A6MZ29","clparkNo":"483841","clplNo":"非固定车位","clmonthType":1,"cardTimr":1498463114000,"protocolTime":1514649600000,"scTime":null,"ckId":null,"mediaNo":null,"jsTime":null,"serialNo":null,"type":1},{"parkWeizhi":"小区外坪","parkFee":150,"clId":1648,"clYezhuId":2185,"clmediaNo":"湘A6BL05","clparkNo":"483841","clplNo":"非固定车位","clmonthType":1,"cardTimr":1498463112000,"protocolTime":1577721600000,"scTime":null,"ckId":null,"mediaNo":null,"jsTime":null,"serialNo":null,"type":1}]
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

    public static class DataBean implements Serializable{
        /**
         * parkWeizhi : 小区外坪
         * parkFee : 150
         * clId : 1646
         * clYezhuId : 2185
         * clmediaNo : 湘A6M93Z
         * clparkNo : 483841
         * clplNo : 非固定车位
         * clmonthType : 1
         * cardTimr : 1498463121000
         * protocolTime : 1514649600000
         * scTime : null
         * ckId : null
         * mediaNo : null
         * jsTime : null
         * serialNo : null
         * type : 1
         */

        private String parkWeizhi;
        private int parkFee;
        private int clId;
        private int clYezhuId;
        private String clmediaNo;
        private String clparkNo;
        private String clplNo;
        private String clmonthType;
        private long cardTimr;
        private long protocolTime;
        private Object scTime;
        private Object ckId;
        private Object mediaNo;
        private Object jsTime;
        private Object serialNo;
        private int type;

        public String getParkWeizhi() {
            return parkWeizhi;
        }

        public void setParkWeizhi(String parkWeizhi) {
            this.parkWeizhi = parkWeizhi;
        }

        public int getParkFee() {
            return parkFee;
        }

        public void setParkFee(int parkFee) {
            this.parkFee = parkFee;
        }

        public int getClId() {
            return clId;
        }

        public void setClId(int clId) {
            this.clId = clId;
        }

        public int getClYezhuId() {
            return clYezhuId;
        }

        public void setClYezhuId(int clYezhuId) {
            this.clYezhuId = clYezhuId;
        }

        public String getClmediaNo() {
            return clmediaNo;
        }

        public void setClmediaNo(String clmediaNo) {
            this.clmediaNo = clmediaNo;
        }

        public String getClparkNo() {
            return clparkNo;
        }

        public void setClparkNo(String clparkNo) {
            this.clparkNo = clparkNo;
        }

        public String getClplNo() {
            return clplNo;
        }

        public void setClplNo(String clplNo) {
            this.clplNo = clplNo;
        }

        public String getClmonthType() {
            return clmonthType;
        }

        public void setClmonthType(String clmonthType) {
            this.clmonthType = clmonthType;
        }

        public long getCardTimr() {
            return cardTimr;
        }

        public void setCardTimr(long cardTimr) {
            this.cardTimr = cardTimr;
        }

        public long getProtocolTime() {
            return protocolTime;
        }

        public void setProtocolTime(long protocolTime) {
            this.protocolTime = protocolTime;
        }

        public Object getScTime() {
            return scTime;
        }

        public void setScTime(Object scTime) {
            this.scTime = scTime;
        }

        public Object getCkId() {
            return ckId;
        }

        public void setCkId(Object ckId) {
            this.ckId = ckId;
        }

        public Object getMediaNo() {
            return mediaNo;
        }

        public void setMediaNo(Object mediaNo) {
            this.mediaNo = mediaNo;
        }

        public Object getJsTime() {
            return jsTime;
        }

        public void setJsTime(Object jsTime) {
            this.jsTime = jsTime;
        }

        public Object getSerialNo() {
            return serialNo;
        }

        public void setSerialNo(Object serialNo) {
            this.serialNo = serialNo;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
