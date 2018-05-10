package com.yxld.yxchuangxin.entity.camera;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * 作者：Android on 2017/10/18
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class ShareFamily extends BaseEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * yezhuBeizhu :
         * yezhuCardNum : 432503199410048057
         * yezhuChuangxinhao : ls13677740
         * yezhuGzdw :
         * yezhuId : 1965
         * yezhuIsUse : 0
         * yezhuLoupan :
         * yezhuName : 肖楠
         * yezhuPhone :
         * yezhuPwd : E10ADC3949BA59ABBE56E057F20F883E
         * yezhuSex :
         * yezhuSfzSrc1 :
         * yezhuSfzSrc2 :
         * yezhuShouji : 13677407464
         * yezhuType : 0
         * yezhuTypeValue :
         * yezhuXmId : 346
         * yezhuBiezhu1 : {"date":3,"day":1,"hours":15,"minutes":47,"month":6,"nanos":0,"seconds":19,"time":1499068039000,"timezoneOffset":-480,"year":117}
         */

        private String yezhuBeizhu;
        private String yezhuCardNum;
        private String yezhuChuangxinhao;
        private String yezhuGzdw;
        private int yezhuId;
        private int yezhuIsUse;
        private String yezhuLoupan;
        private String yezhuName;
        private String yezhuPhone;
        private String yezhuPwd;
        private String yezhuSex;
        private String yezhuSfzSrc1;
        private String yezhuSfzSrc2;
        private String yezhuShouji;
        private int yezhuType;
        private String yezhuTypeValue;
        private int yezhuXmId;
        private String yezhuBiezhu1;

        public String getYezhuBeizhu() {
            return yezhuBeizhu;
        }

        public void setYezhuBeizhu(String yezhuBeizhu) {
            this.yezhuBeizhu = yezhuBeizhu;
        }

        public String getYezhuCardNum() {
            return yezhuCardNum;
        }

        public void setYezhuCardNum(String yezhuCardNum) {
            this.yezhuCardNum = yezhuCardNum;
        }

        public String getYezhuChuangxinhao() {
            return yezhuChuangxinhao;
        }

        public void setYezhuChuangxinhao(String yezhuChuangxinhao) {
            this.yezhuChuangxinhao = yezhuChuangxinhao;
        }

        public String getYezhuGzdw() {
            return yezhuGzdw;
        }

        public void setYezhuGzdw(String yezhuGzdw) {
            this.yezhuGzdw = yezhuGzdw;
        }

        public int getYezhuId() {
            return yezhuId;
        }

        public void setYezhuId(int yezhuId) {
            this.yezhuId = yezhuId;
        }

        public int getYezhuIsUse() {
            return yezhuIsUse;
        }

        public void setYezhuIsUse(int yezhuIsUse) {
            this.yezhuIsUse = yezhuIsUse;
        }

        public String getYezhuLoupan() {
            return yezhuLoupan;
        }

        public void setYezhuLoupan(String yezhuLoupan) {
            this.yezhuLoupan = yezhuLoupan;
        }

        public String getYezhuName() {
            return yezhuName;
        }

        public void setYezhuName(String yezhuName) {
            this.yezhuName = yezhuName;
        }

        public String getYezhuPhone() {
            return yezhuPhone;
        }

        public void setYezhuPhone(String yezhuPhone) {
            this.yezhuPhone = yezhuPhone;
        }

        public String getYezhuPwd() {
            return yezhuPwd;
        }

        public void setYezhuPwd(String yezhuPwd) {
            this.yezhuPwd = yezhuPwd;
        }

        public String getYezhuSex() {
            return yezhuSex;
        }

        public void setYezhuSex(String yezhuSex) {
            this.yezhuSex = yezhuSex;
        }

        public String getYezhuSfzSrc1() {
            return yezhuSfzSrc1;
        }

        public void setYezhuSfzSrc1(String yezhuSfzSrc1) {
            this.yezhuSfzSrc1 = yezhuSfzSrc1;
        }

        public String getYezhuSfzSrc2() {
            return yezhuSfzSrc2;
        }

        public void setYezhuSfzSrc2(String yezhuSfzSrc2) {
            this.yezhuSfzSrc2 = yezhuSfzSrc2;
        }

        public String getYezhuShouji() {
            return yezhuShouji;
        }

        public void setYezhuShouji(String yezhuShouji) {
            this.yezhuShouji = yezhuShouji;
        }

        public int getYezhuType() {
            return yezhuType;
        }

        public void setYezhuType(int yezhuType) {
            this.yezhuType = yezhuType;
        }

        public String getYezhuTypeValue() {
            return yezhuTypeValue;
        }

        public void setYezhuTypeValue(String yezhuTypeValue) {
            this.yezhuTypeValue = yezhuTypeValue;
        }

        public int getYezhuXmId() {
            return yezhuXmId;
        }

        public void setYezhuXmId(int yezhuXmId) {
            this.yezhuXmId = yezhuXmId;
        }

        public String getYezhuBiezhu1() {
            return yezhuBiezhu1;
        }

        public void setYezhuBiezhu1(String yezhuBiezhu1) {
            this.yezhuBiezhu1 = yezhuBiezhu1;
        }

        public static class YezhuBiezhu1Bean {
            /**
             * date : 3
             * day : 1
             * hours : 15
             * minutes : 47
             * month : 6
             * nanos : 0
             * seconds : 19
             * time : 1499068039000
             * timezoneOffset : -480
             * year : 117
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int nanos;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }
    }
}
