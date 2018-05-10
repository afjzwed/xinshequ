package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * Created by William on 2017/12/19.
 */

public class RimCommentListBean extends BaseEntity {

    /**
     * data : {"pingjiaList":[{"orderBusinessNumber":"1003",
     * "orderNumber":"002184sj1513302245922","orderUserName":"庾亮","orderUserPhone":"15575125570",
     * "orderEvaluateEvaTime":"2017-12-15 10:29:24.0",
     * "orderEvaluateEvaContent":"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈好哈哈哈哈说说话哈哈哈哈哈哈哈哈哈哈","orderEvaluateEvaLevel":5,
     * "orderEvaluateReplyContent":null,"orderEvaluateReplyTime":null,
     * "orderEvaluateReplyPerson":null}],"score":5}
     * success : 1
     * total : 0
     */

    private DataBean data;
    private String success;
    private int total;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static class DataBean {
        /**
         * pingjiaList : [{"orderBusinessNumber":"1003","orderNumber":"002184sj1513302245922",
         * "orderUserName":"庾亮","orderUserPhone":"15575125570","orderEvaluateEvaTime":"2017-12-15
         * 10:29:24.0","orderEvaluateEvaContent":"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈好哈哈哈哈说说话哈哈哈哈哈哈哈哈哈哈",
         * "orderEvaluateEvaLevel":5,"orderEvaluateReplyContent":null,
         * "orderEvaluateReplyTime":null,"orderEvaluateReplyPerson":null}]
         * score : 5
         */

        private double score;
        private List<PingjiaListBean> pingjiaList;


        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public List<PingjiaListBean> getPingjiaList() {
            return pingjiaList;
        }

        public void setPingjiaList(List<PingjiaListBean> pingjiaList) {
            this.pingjiaList = pingjiaList;
        }

        public static class PingjiaListBean {
            /**
             * orderBusinessNumber : 1003
             * orderNumber : 002184sj1513302245922
             * orderUserName : 庾亮
             * orderUserPhone : 15575125570
             * orderEvaluateEvaTime : 2017-12-15 10:29:24.0
             * orderEvaluateEvaContent : 哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈好哈哈哈哈说说话哈哈哈哈哈哈哈哈哈哈
             * orderEvaluateEvaLevel : 5
             * orderEvaluateReplyContent : null
             * orderEvaluateReplyTime : null
             * orderEvaluateReplyPerson : null
             */

            private String orderBusinessNumber;
            private String orderNumber;
            private String orderUserName;
            private String orderUserPhone;
            private String orderEvaluateEvaTime;
            private String orderEvaluateEvaContent;
            private int orderEvaluateEvaLevel;
            private String orderEvaluateReplyContent;
            private String orderEvaluateReplyTime;
            private String orderEvaluateReplyPerson;

            public String getOrderBusinessNumber() {
                return orderBusinessNumber;
            }

            public void setOrderBusinessNumber(String orderBusinessNumber) {
                this.orderBusinessNumber = orderBusinessNumber;
            }

            public String getOrderNumber() {
                return orderNumber;
            }

            public void setOrderNumber(String orderNumber) {
                this.orderNumber = orderNumber;
            }

            public String getOrderUserName() {
                return orderUserName;
            }

            public void setOrderUserName(String orderUserName) {
                this.orderUserName = orderUserName;
            }

            public String getOrderUserPhone() {
                return orderUserPhone;
            }

            public void setOrderUserPhone(String orderUserPhone) {
                this.orderUserPhone = orderUserPhone;
            }

            public String getOrderEvaluateEvaTime() {
                return orderEvaluateEvaTime;
            }

            public void setOrderEvaluateEvaTime(String orderEvaluateEvaTime) {
                this.orderEvaluateEvaTime = orderEvaluateEvaTime;
            }

            public String getOrderEvaluateEvaContent() {
                return orderEvaluateEvaContent;
            }

            public void setOrderEvaluateEvaContent(String orderEvaluateEvaContent) {
                this.orderEvaluateEvaContent = orderEvaluateEvaContent;
            }

            public int getOrderEvaluateEvaLevel() {
                return orderEvaluateEvaLevel;
            }

            public void setOrderEvaluateEvaLevel(int orderEvaluateEvaLevel) {
                this.orderEvaluateEvaLevel = orderEvaluateEvaLevel;
            }

            public String getOrderEvaluateReplyContent() {
                return orderEvaluateReplyContent;
            }

            public void setOrderEvaluateReplyContent(String orderEvaluateReplyContent) {
                this.orderEvaluateReplyContent = orderEvaluateReplyContent;
            }

            public String getOrderEvaluateReplyTime() {
                return orderEvaluateReplyTime;
            }

            public void setOrderEvaluateReplyTime(String orderEvaluateReplyTime) {
                this.orderEvaluateReplyTime = orderEvaluateReplyTime;
            }

            public String getOrderEvaluateReplyPerson() {
                return orderEvaluateReplyPerson;
            }

            public void setOrderEvaluateReplyPerson(String orderEvaluateReplyPerson) {
                this.orderEvaluateReplyPerson = orderEvaluateReplyPerson;
            }

            @Override
            public String toString() {
                return "PingjiaListBean{" +
                        "orderBusinessNumber='" + orderBusinessNumber + '\'' +
                        ", orderNumber='" + orderNumber + '\'' +
                        ", orderUserName='" + orderUserName + '\'' +
                        ", orderUserPhone='" + orderUserPhone + '\'' +
                        ", orderEvaluateEvaTime='" + orderEvaluateEvaTime + '\'' +
                        ", orderEvaluateEvaContent='" + orderEvaluateEvaContent + '\'' +
                        ", orderEvaluateEvaLevel=" + orderEvaluateEvaLevel +
                        ", orderEvaluateReplyContent='" + orderEvaluateReplyContent + '\'' +
                        ", orderEvaluateReplyTime='" + orderEvaluateReplyTime + '\'' +
                        ", orderEvaluateReplyPerson='" + orderEvaluateReplyPerson + '\'' +
                        '}';
            }
        }
    }
}
