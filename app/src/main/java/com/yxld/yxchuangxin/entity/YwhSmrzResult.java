package com.yxld.yxchuangxin.entity;

import com.google.gson.annotations.SerializedName;
import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/11/13.
 */

public class YwhSmrzResult extends BaseEntity {

    /**
     * data : {"status":3,"area":200,"cardFront":null,"cardReverse":"android_ywh/1542114338332","deedFront":["",""],"paperwork":["",""]}
     */
    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * status : 3
         * area : 200
         * cardFront : null
         * cardReverse : android_ywh/1542114338332
         * deedFront : ["",""]
         * paperwork : ["",""]
         */

        @SerializedName("status") private int statusX;
        private int area;
        private String cardFront;
        private String cardReverse;
        private List<String> deedFront;
        private List<String> paperwork;

        public int getStatusX() {
            return statusX;
        }

        public void setStatusX(int statusX) {
            this.statusX = statusX;
        }

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
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

        public List<String> getDeedFront() {
            return deedFront;
        }

        public void setDeedFront(List<String> deedFront) {
            this.deedFront = deedFront;
        }

        public List<String> getPaperwork() {
            return paperwork;
        }

        public void setPaperwork(List<String> paperwork) {
            this.paperwork = paperwork;
        }
    }
}
