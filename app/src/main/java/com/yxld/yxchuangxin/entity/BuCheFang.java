package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * 作者：Android on 2017/9/7
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class BuCheFang extends BaseEntity {

    /**
     * data : {"buchefangState":"1","endTime":"19:30","fangquhao":1,"startTime":"9:30","weeks":"星期四星期天"}
     * success : true
     */

    private DataBean data;
    private boolean success;

    private List<DataBean> list;

    public List<DataBean> getList() {
        return list;
    }

    public void setList(List<DataBean> list) {
        this.list = list;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * buchefangState : 1
         * endTime : 19:30
         * fangquhao : 1
         * startTime : 9:30
         * weeks : 星期四星期天
         */

        private String buchefangState;
        private String endTime;
        private int fangquhao;
        private String startTime;
        private String weeks;

        public String getBuchefangState() {
            return buchefangState;
        }

        public void setBuchefangState(String buchefangState) {
            this.buchefangState = buchefangState;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getFangquhao() {
            return fangquhao;
        }

        public void setFangquhao(int fangquhao) {
            this.fangquhao = fangquhao;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getWeeks() {
            return weeks;
        }

        public void setWeeks(String weeks) {
            this.weeks = weeks;
        }
    }
}
