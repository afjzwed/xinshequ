package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * 作者：yishangfei on 2016/12/29 0029 16:18
 * 邮箱：yishangfei@foxmail.com
 */
public class AppWuYeFei extends BaseEntity {


    /**
     * MSG : 查找成功
     * status : 0
     * house : [{"fees":"256.86","lateFees":"0","status":"已结清","time":"2016年1月"},{"fees":"256.86","lateFees":"0","status":"已结清","time":"2016年2月"},{"fees":"256.86","lateFees":"0","status":"已结清","time":"2016年3月"},{"fees":"256.86","lateFees":"0","status":"已结清","time":"2016年4月"},{"fees":"256.86","lateFees":"0","status":"已结清","time":"2016年5月"},{"fees":"256.86","lateFees":"0","status":"已结清","time":"2016年6月"},{"fees":"256.86","lateFees":"0","status":"已结清","time":"2016年7月"},{"fees":"256.86","lateFees":"0","status":"已结清","time":"2016年8月"},{"fees":"256.86","lateFees":"0","status":"已结清","time":"2016年9月"},{"fees":"256.86","lateFees":"0","status":"已结清","time":"2016年10月"},{"fees":"256.86","lateFees":"0","status":"已结清","time":"2016年11月"}]
     */

    private List<AppWuYeFei> house;

    public List<AppWuYeFei> getHouse() {
        return house;
    }

    public void setHouse(List<AppWuYeFei> house) {
        this.house = house;
    }

        /**
         * fees : 256.86
         * lateFees : 0
         * status : 已结清
         * time : 2016年1月
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
