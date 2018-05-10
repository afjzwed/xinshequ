package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * 作者：hu on 2017/7/4
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class JiaofeiMingxi extends BaseEntity {

    /**
     * house : [{"fees":"","lateFees":"","statusM":"已结清","time":"2017-1"},{"fees":"","lateFees":"","statusM":"已结清","time":"2017-2"},{"fees":"","lateFees":"","statusM":"已结清","time":"2017-3"},{"fees":"","lateFees":"","statusM":"已结清","time":"2017-4"},{"fees":"4.0","lateFees":"","statusM":"未缴费","time":"2017-5"},{"fees":"4.0","lateFees":"","statusM":"未缴费","time":"2017-6"},{"fees":"4.0","lateFees":"","statusM":"未缴费","time":"2017-7"}]
     * status : 0
     */

    private List<HouseBean> house;

    public List<HouseBean> getHouse() {
        return house;
    }

    public void setHouse(List<HouseBean> house) {
        this.house = house;
    }

    public static class HouseBean {
        /**
         * fees :
         * lateFees :
         * statusM : 已结清
         * time : 2017-1
         */

        private String fees;
        private String lateFees;
        private String statusM;
        private String time;

        public String getFees() {
            return fees;
        }

        public void setFees(String fees) {
            this.fees = fees;
        }

        public String getLateFees() {
            return lateFees;
        }

        public void setLateFees(String lateFees) {
            this.lateFees = lateFees;
        }

        public String getStatusM() {
            return statusM;
        }

        public void setStatusM(String statusM) {
            this.statusM = statusM;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
