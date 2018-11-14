package com.yxld.yxchuangxin.entity;

import com.google.gson.annotations.SerializedName;
import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * Created by William on 2018/11/14.
 */

public class HouxuanRenBean extends BaseEntity {

    /**
     * success : true
     * code : 200
     * error : null
     * data : [{"id":1149,"ownerName":"李涛","cardNumber":"430521199510165236","phone":"","cardFront":"",
     * "cardReverse":"","deedFront":"","paperwork":"","status":2,"aduitId":null,"aduitName":null,"aduitTime":null,
     * "reportDate":"2018-11-09 18:05:40.0","isElect":1,"projectId":346,"yezhuType":0,"yezhuGzdw":"","descs":"",
     * "isUse":0,"pwd":"e10adc3949ba59abbe56e057f20f883e","chuangxinhao":null,"projectbianhao":"","aduitOpnion":null}]
     * rows : [{"id":1149,"ownerName":"李涛","cardNumber":"430521199510165236","phone":"","cardFront":"",
     * "cardReverse":"","deedFront":"","paperwork":"","status":2,"aduitId":null,"aduitName":null,"aduitTime":null,
     * "reportDate":"2018-11-09 18:05:40.0","isElect":1,"projectId":346,"yezhuType":0,"yezhuGzdw":"","descs":"",
     * "isUse":0,"pwd":"e10adc3949ba59abbe56e057f20f883e","chuangxinhao":null,"projectbianhao":"","aduitOpnion":null}]
     * total : null
     * token : null
     */

    private boolean success;
    private int code;
    private Object error;
    private Object total;
    private Object token;
    private List<DataBean> data;
    private List<RowsBean> rows;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class DataBean {
        /**
         * id : 1149
         * ownerName : 李涛
         * cardNumber : 430521199510165236
         * phone :
         * cardFront :
         * cardReverse :
         * deedFront :
         * paperwork :
         * status : 2
         * aduitId : null
         * aduitName : null
         * aduitTime : null
         * reportDate : 2018-11-09 18:05:40.0
         * isElect : 1
         * projectId : 346
         * yezhuType : 0
         * yezhuGzdw :
         * descs :
         * isUse : 0
         * pwd : e10adc3949ba59abbe56e057f20f883e
         * chuangxinhao : null
         * projectbianhao :
         * aduitOpnion : null
         */

        private int id;
        private String ownerName;
        private String cardNumber;
        private String phone;
        private String cardFront;
        private String cardReverse;
        private String deedFront;
        private String paperwork;
        @SerializedName("status")
        private int statusX;
        private Object aduitId;
        private Object aduitName;
        private Object aduitTime;
        private String reportDate;
        private int isElect;
        private int projectId;
        private int yezhuType;
        private String yezhuGzdw;
        private String descs;
        private int isUse;
        private String pwd;
        private Object chuangxinhao;
        private String projectbianhao;
        private Object aduitOpnion;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCardFront() {
            return cardFront;
        }

        public void setCardFront(String cardFront) {
            this.cardFront = cardFront;
        }

        public String getCardReverse() {
            return cardReverse;
        }

        public void setCardReverse(String cardReverse) {
            this.cardReverse = cardReverse;
        }

        public String getDeedFront() {
            return deedFront;
        }

        public void setDeedFront(String deedFront) {
            this.deedFront = deedFront;
        }

        public String getPaperwork() {
            return paperwork;
        }

        public void setPaperwork(String paperwork) {
            this.paperwork = paperwork;
        }

        public int getStatusX() {
            return statusX;
        }

        public void setStatusX(int statusX) {
            this.statusX = statusX;
        }

        public Object getAduitId() {
            return aduitId;
        }

        public void setAduitId(Object aduitId) {
            this.aduitId = aduitId;
        }

        public Object getAduitName() {
            return aduitName;
        }

        public void setAduitName(Object aduitName) {
            this.aduitName = aduitName;
        }

        public Object getAduitTime() {
            return aduitTime;
        }

        public void setAduitTime(Object aduitTime) {
            this.aduitTime = aduitTime;
        }

        public String getReportDate() {
            return reportDate;
        }

        public void setReportDate(String reportDate) {
            this.reportDate = reportDate;
        }

        public int getIsElect() {
            return isElect;
        }

        public void setIsElect(int isElect) {
            this.isElect = isElect;
        }

        public int getProjectId() {
            return projectId;
        }

        public void setProjectId(int projectId) {
            this.projectId = projectId;
        }

        public int getYezhuType() {
            return yezhuType;
        }

        public void setYezhuType(int yezhuType) {
            this.yezhuType = yezhuType;
        }

        public String getYezhuGzdw() {
            return yezhuGzdw;
        }

        public void setYezhuGzdw(String yezhuGzdw) {
            this.yezhuGzdw = yezhuGzdw;
        }

        public String getDescs() {
            return descs;
        }

        public void setDescs(String descs) {
            this.descs = descs;
        }

        public int getIsUse() {
            return isUse;
        }

        public void setIsUse(int isUse) {
            this.isUse = isUse;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public Object getChuangxinhao() {
            return chuangxinhao;
        }

        public void setChuangxinhao(Object chuangxinhao) {
            this.chuangxinhao = chuangxinhao;
        }

        public String getProjectbianhao() {
            return projectbianhao;
        }

        public void setProjectbianhao(String projectbianhao) {
            this.projectbianhao = projectbianhao;
        }

        public Object getAduitOpnion() {
            return aduitOpnion;
        }

        public void setAduitOpnion(Object aduitOpnion) {
            this.aduitOpnion = aduitOpnion;
        }
    }

    public static class RowsBean {
        /**
         * id : 1149
         * ownerName : 李涛
         * cardNumber : 430521199510165236
         * phone :
         * cardFront :
         * cardReverse :
         * deedFront :
         * paperwork :
         * status : 2
         * aduitId : null
         * aduitName : null
         * aduitTime : null
         * reportDate : 2018-11-09 18:05:40.0
         * isElect : 1
         * projectId : 346
         * yezhuType : 0
         * yezhuGzdw :
         * descs :
         * isUse : 0
         * pwd : e10adc3949ba59abbe56e057f20f883e
         * chuangxinhao : null
         * projectbianhao :
         * aduitOpnion : null
         */

        private int id;
        private String ownerName;
        private String cardNumber;
        private String phone;
        private String cardFront;
        private String cardReverse;
        private String deedFront;
        private String paperwork;
        @SerializedName("status")
        private int statusX;
        private Object aduitId;
        private Object aduitName;
        private Object aduitTime;
        private String reportDate;
        private int isElect;
        private int projectId;
        private int yezhuType;
        private String yezhuGzdw;
        private String descs;
        private int isUse;
        private String pwd;
        private Object chuangxinhao;
        private String projectbianhao;
        private Object aduitOpnion;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCardFront() {
            return cardFront;
        }

        public void setCardFront(String cardFront) {
            this.cardFront = cardFront;
        }

        public String getCardReverse() {
            return cardReverse;
        }

        public void setCardReverse(String cardReverse) {
            this.cardReverse = cardReverse;
        }

        public String getDeedFront() {
            return deedFront;
        }

        public void setDeedFront(String deedFront) {
            this.deedFront = deedFront;
        }

        public String getPaperwork() {
            return paperwork;
        }

        public void setPaperwork(String paperwork) {
            this.paperwork = paperwork;
        }

        public int getStatusX() {
            return statusX;
        }

        public void setStatusX(int statusX) {
            this.statusX = statusX;
        }

        public Object getAduitId() {
            return aduitId;
        }

        public void setAduitId(Object aduitId) {
            this.aduitId = aduitId;
        }

        public Object getAduitName() {
            return aduitName;
        }

        public void setAduitName(Object aduitName) {
            this.aduitName = aduitName;
        }

        public Object getAduitTime() {
            return aduitTime;
        }

        public void setAduitTime(Object aduitTime) {
            this.aduitTime = aduitTime;
        }

        public String getReportDate() {
            return reportDate;
        }

        public void setReportDate(String reportDate) {
            this.reportDate = reportDate;
        }

        public int getIsElect() {
            return isElect;
        }

        public void setIsElect(int isElect) {
            this.isElect = isElect;
        }

        public int getProjectId() {
            return projectId;
        }

        public void setProjectId(int projectId) {
            this.projectId = projectId;
        }

        public int getYezhuType() {
            return yezhuType;
        }

        public void setYezhuType(int yezhuType) {
            this.yezhuType = yezhuType;
        }

        public String getYezhuGzdw() {
            return yezhuGzdw;
        }

        public void setYezhuGzdw(String yezhuGzdw) {
            this.yezhuGzdw = yezhuGzdw;
        }

        public String getDescs() {
            return descs;
        }

        public void setDescs(String descs) {
            this.descs = descs;
        }

        public int getIsUse() {
            return isUse;
        }

        public void setIsUse(int isUse) {
            this.isUse = isUse;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public Object getChuangxinhao() {
            return chuangxinhao;
        }

        public void setChuangxinhao(Object chuangxinhao) {
            this.chuangxinhao = chuangxinhao;
        }

        public String getProjectbianhao() {
            return projectbianhao;
        }

        public void setProjectbianhao(String projectbianhao) {
            this.projectbianhao = projectbianhao;
        }

        public Object getAduitOpnion() {
            return aduitOpnion;
        }

        public void setAduitOpnion(Object aduitOpnion) {
            this.aduitOpnion = aduitOpnion;
        }
    }
}
