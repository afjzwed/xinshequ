package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * Created by William on 2018/2/8.
 */

public class RimOrderCommentBean extends BaseEntity {

    /**
     * data : [{"orderId":null,"orderBusinessName":"快快洗","orderBusinessNumber":null,
     * "orderNumber":"003407sj1514344117939","orderUserId":null,"orderUserName":null,
     * "orderUserPhone":null,"orderUserAddress":null,"orderCompanyId":null,
     * "orderUserCompany":null,"orderXiangmuId":null,"orderUserXiangmu":null,"orderStatus":null,
     * "orderOrderTime":null,"orderMoney":null,"orderFactMoney":null,"orderSettleMoney":null,
     * "orderSenderId":null,"orderSenderName":null,"orderSenderPhone":null,"orderSendMoney":null,
     * "orderEvaluateEvaTime":null,"orderEvaluateEvaContent":null,"orderEvaluateEvaLevel":null,
     * "orderEvaluateReplyContent":null,"orderEvaluateReplyTime":null,
     * "orderEvaluateReplyPerson":null,"orderCancelRemark":null,"orderPayType":null,
     * "orderPayTime":null,"orderPayTrading":null,"orderBespeakTime":null,
     * "orderDeliverMethods":null,"orderSettleStatus":null,"orderPaydeliverfee":null,
     * "orderIsDelete":null,"orderSettleTime":null,"orderSettlePerson":null,"orderRemark":null,
     * "sendStatus":null,"business_logo":"upload/img/1510126468656;123456","discountMoney":null,
     * "discountContent":null,"produceType":null,"shTime":null,"businessPhone":null,
     * "detailList":null,"productNames":null}]
     * success : 1
     * total : 0
     */

    private String success;
    private int total;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * orderId : null
         * orderBusinessName : 快快洗
         * orderBusinessNumber : null
         * orderNumber : 003407sj1514344117939
         * orderUserId : null
         * orderUserName : null
         * orderUserPhone : null
         * orderUserAddress : null
         * orderCompanyId : null
         * orderUserCompany : null
         * orderXiangmuId : null
         * orderUserXiangmu : null
         * orderStatus : null
         * orderOrderTime : null
         * orderMoney : null
         * orderFactMoney : null
         * orderSettleMoney : null
         * orderSenderId : null
         * orderSenderName : null
         * orderSenderPhone : null
         * orderSendMoney : null
         * orderEvaluateEvaTime : null
         * orderEvaluateEvaContent : null
         * orderEvaluateEvaLevel : null
         * orderEvaluateReplyContent : null
         * orderEvaluateReplyTime : null
         * orderEvaluateReplyPerson : null
         * orderCancelRemark : null
         * orderPayType : null
         * orderPayTime : null
         * orderPayTrading : null
         * orderBespeakTime : null
         * orderDeliverMethods : null
         * orderSettleStatus : null
         * orderPaydeliverfee : null
         * orderIsDelete : null
         * orderSettleTime : null
         * orderSettlePerson : null
         * orderRemark : null
         * sendStatus : null
         * business_logo : upload/img/1510126468656;123456
         * discountMoney : null
         * discountContent : null
         * produceType : null
         * shTime : null
         * businessPhone : null
         * detailList : null
         * productNames : null
         */

        private Object orderId;
        private String orderBusinessName;//商家名称
        private Object orderBusinessNumber;
        private String orderNumber;//订单编号
        private Object orderUserId;
        private String orderUserName;
        private Object orderUserPhone;
        private Object orderUserAddress;
        private Object orderCompanyId;
        private Object orderUserCompany;
        private Object orderXiangmuId;
        private Object orderUserXiangmu;
        private Object orderStatus;
        private Object orderOrderTime;
        private Object orderMoney;
        private Object orderFactMoney;
        private Object orderSettleMoney;
        private Object orderSenderId;
        private Object orderSenderName;
        private Object orderSenderPhone;
        private Object orderSendMoney;
        private String orderEvaluateEvaTime;//评价时间
        private String orderEvaluateEvaContent;//评价内容
        private int orderEvaluateEvaLevel;//评价等级
        private String orderEvaluateReplyContent;//回复内容
        private Object orderEvaluateReplyTime;
        private Object orderEvaluateReplyPerson;
        private Object orderCancelRemark;
        private Object orderPayType;
        private Object orderPayTime;
        private Object orderPayTrading;
        private Object orderBespeakTime;
        private Object orderDeliverMethods;
        private Object orderSettleStatus;
        private Object orderPaydeliverfee;
        private Object orderIsDelete;
        private Object orderSettleTime;
        private Object orderSettlePerson;
        private Object orderRemark;
        private Object sendStatus;
        private String business_logo;//商家logo图
        private Object discountMoney;
        private Object discountContent;
        private Object produceType;
        private Object shTime;
        private Object businessPhone;
        private Object detailList;
        private Object productNames;

        public Object getOrderId() {
            return orderId;
        }

        public void setOrderId(Object orderId) {
            this.orderId = orderId;
        }

        public String getOrderBusinessName() {
            return orderBusinessName;
        }

        public void setOrderBusinessName(String orderBusinessName) {
            this.orderBusinessName = orderBusinessName;
        }

        public Object getOrderBusinessNumber() {
            return orderBusinessNumber;
        }

        public void setOrderBusinessNumber(Object orderBusinessNumber) {
            this.orderBusinessNumber = orderBusinessNumber;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public Object getOrderUserId() {
            return orderUserId;
        }

        public void setOrderUserId(Object orderUserId) {
            this.orderUserId = orderUserId;
        }

        public String getOrderUserName() {
            return orderUserName;
        }

        public void setOrderUserName(String orderUserName) {
            this.orderUserName = orderUserName;
        }

        public Object getOrderUserPhone() {
            return orderUserPhone;
        }

        public void setOrderUserPhone(Object orderUserPhone) {
            this.orderUserPhone = orderUserPhone;
        }

        public Object getOrderUserAddress() {
            return orderUserAddress;
        }

        public void setOrderUserAddress(Object orderUserAddress) {
            this.orderUserAddress = orderUserAddress;
        }

        public Object getOrderCompanyId() {
            return orderCompanyId;
        }

        public void setOrderCompanyId(Object orderCompanyId) {
            this.orderCompanyId = orderCompanyId;
        }

        public Object getOrderUserCompany() {
            return orderUserCompany;
        }

        public void setOrderUserCompany(Object orderUserCompany) {
            this.orderUserCompany = orderUserCompany;
        }

        public Object getOrderXiangmuId() {
            return orderXiangmuId;
        }

        public void setOrderXiangmuId(Object orderXiangmuId) {
            this.orderXiangmuId = orderXiangmuId;
        }

        public Object getOrderUserXiangmu() {
            return orderUserXiangmu;
        }

        public void setOrderUserXiangmu(Object orderUserXiangmu) {
            this.orderUserXiangmu = orderUserXiangmu;
        }

        public Object getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(Object orderStatus) {
            this.orderStatus = orderStatus;
        }

        public Object getOrderOrderTime() {
            return orderOrderTime;
        }

        public void setOrderOrderTime(Object orderOrderTime) {
            this.orderOrderTime = orderOrderTime;
        }

        public Object getOrderMoney() {
            return orderMoney;
        }

        public void setOrderMoney(Object orderMoney) {
            this.orderMoney = orderMoney;
        }

        public Object getOrderFactMoney() {
            return orderFactMoney;
        }

        public void setOrderFactMoney(Object orderFactMoney) {
            this.orderFactMoney = orderFactMoney;
        }

        public Object getOrderSettleMoney() {
            return orderSettleMoney;
        }

        public void setOrderSettleMoney(Object orderSettleMoney) {
            this.orderSettleMoney = orderSettleMoney;
        }

        public Object getOrderSenderId() {
            return orderSenderId;
        }

        public void setOrderSenderId(Object orderSenderId) {
            this.orderSenderId = orderSenderId;
        }

        public Object getOrderSenderName() {
            return orderSenderName;
        }

        public void setOrderSenderName(Object orderSenderName) {
            this.orderSenderName = orderSenderName;
        }

        public Object getOrderSenderPhone() {
            return orderSenderPhone;
        }

        public void setOrderSenderPhone(Object orderSenderPhone) {
            this.orderSenderPhone = orderSenderPhone;
        }

        public Object getOrderSendMoney() {
            return orderSendMoney;
        }

        public void setOrderSendMoney(Object orderSendMoney) {
            this.orderSendMoney = orderSendMoney;
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

        public Object getOrderEvaluateReplyTime() {
            return orderEvaluateReplyTime;
        }

        public void setOrderEvaluateReplyTime(Object orderEvaluateReplyTime) {
            this.orderEvaluateReplyTime = orderEvaluateReplyTime;
        }

        public Object getOrderEvaluateReplyPerson() {
            return orderEvaluateReplyPerson;
        }

        public void setOrderEvaluateReplyPerson(Object orderEvaluateReplyPerson) {
            this.orderEvaluateReplyPerson = orderEvaluateReplyPerson;
        }

        public Object getOrderCancelRemark() {
            return orderCancelRemark;
        }

        public void setOrderCancelRemark(Object orderCancelRemark) {
            this.orderCancelRemark = orderCancelRemark;
        }

        public Object getOrderPayType() {
            return orderPayType;
        }

        public void setOrderPayType(Object orderPayType) {
            this.orderPayType = orderPayType;
        }

        public Object getOrderPayTime() {
            return orderPayTime;
        }

        public void setOrderPayTime(Object orderPayTime) {
            this.orderPayTime = orderPayTime;
        }

        public Object getOrderPayTrading() {
            return orderPayTrading;
        }

        public void setOrderPayTrading(Object orderPayTrading) {
            this.orderPayTrading = orderPayTrading;
        }

        public Object getOrderBespeakTime() {
            return orderBespeakTime;
        }

        public void setOrderBespeakTime(Object orderBespeakTime) {
            this.orderBespeakTime = orderBespeakTime;
        }

        public Object getOrderDeliverMethods() {
            return orderDeliverMethods;
        }

        public void setOrderDeliverMethods(Object orderDeliverMethods) {
            this.orderDeliverMethods = orderDeliverMethods;
        }

        public Object getOrderSettleStatus() {
            return orderSettleStatus;
        }

        public void setOrderSettleStatus(Object orderSettleStatus) {
            this.orderSettleStatus = orderSettleStatus;
        }

        public Object getOrderPaydeliverfee() {
            return orderPaydeliverfee;
        }

        public void setOrderPaydeliverfee(Object orderPaydeliverfee) {
            this.orderPaydeliverfee = orderPaydeliverfee;
        }

        public Object getOrderIsDelete() {
            return orderIsDelete;
        }

        public void setOrderIsDelete(Object orderIsDelete) {
            this.orderIsDelete = orderIsDelete;
        }

        public Object getOrderSettleTime() {
            return orderSettleTime;
        }

        public void setOrderSettleTime(Object orderSettleTime) {
            this.orderSettleTime = orderSettleTime;
        }

        public Object getOrderSettlePerson() {
            return orderSettlePerson;
        }

        public void setOrderSettlePerson(Object orderSettlePerson) {
            this.orderSettlePerson = orderSettlePerson;
        }

        public Object getOrderRemark() {
            return orderRemark;
        }

        public void setOrderRemark(Object orderRemark) {
            this.orderRemark = orderRemark;
        }

        public Object getSendStatus() {
            return sendStatus;
        }

        public void setSendStatus(Object sendStatus) {
            this.sendStatus = sendStatus;
        }

        public String getBusiness_logo() {
            return business_logo;
        }

        public void setBusiness_logo(String business_logo) {
            this.business_logo = business_logo;
        }

        public Object getDiscountMoney() {
            return discountMoney;
        }

        public void setDiscountMoney(Object discountMoney) {
            this.discountMoney = discountMoney;
        }

        public Object getDiscountContent() {
            return discountContent;
        }

        public void setDiscountContent(Object discountContent) {
            this.discountContent = discountContent;
        }

        public Object getProduceType() {
            return produceType;
        }

        public void setProduceType(Object produceType) {
            this.produceType = produceType;
        }

        public Object getShTime() {
            return shTime;
        }

        public void setShTime(Object shTime) {
            this.shTime = shTime;
        }

        public Object getBusinessPhone() {
            return businessPhone;
        }

        public void setBusinessPhone(Object businessPhone) {
            this.businessPhone = businessPhone;
        }

        public Object getDetailList() {
            return detailList;
        }

        public void setDetailList(Object detailList) {
            this.detailList = detailList;
        }

        public Object getProductNames() {
            return productNames;
        }

        public void setProductNames(Object productNames) {
            this.productNames = productNames;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "orderId=" + orderId +
                    ", orderBusinessName='" + orderBusinessName + '\'' +
                    ", orderBusinessNumber=" + orderBusinessNumber +
                    ", orderNumber='" + orderNumber + '\'' +
                    ", orderUserId=" + orderUserId +
                    ", orderUserName=" + orderUserName +
                    ", orderUserPhone=" + orderUserPhone +
                    ", orderUserAddress=" + orderUserAddress +
                    ", orderCompanyId=" + orderCompanyId +
                    ", orderUserCompany=" + orderUserCompany +
                    ", orderXiangmuId=" + orderXiangmuId +
                    ", orderUserXiangmu=" + orderUserXiangmu +
                    ", orderStatus=" + orderStatus +
                    ", orderOrderTime=" + orderOrderTime +
                    ", orderMoney=" + orderMoney +
                    ", orderFactMoney=" + orderFactMoney +
                    ", orderSettleMoney=" + orderSettleMoney +
                    ", orderSenderId=" + orderSenderId +
                    ", orderSenderName=" + orderSenderName +
                    ", orderSenderPhone=" + orderSenderPhone +
                    ", orderSendMoney=" + orderSendMoney +
                    ", orderEvaluateEvaTime='" + orderEvaluateEvaTime + '\'' +
                    ", orderEvaluateEvaContent='" + orderEvaluateEvaContent + '\'' +
                    ", orderEvaluateEvaLevel=" + orderEvaluateEvaLevel +
                    ", orderEvaluateReplyContent='" + orderEvaluateReplyContent + '\'' +
                    ", orderEvaluateReplyTime=" + orderEvaluateReplyTime +
                    ", orderEvaluateReplyPerson=" + orderEvaluateReplyPerson +
                    ", orderCancelRemark=" + orderCancelRemark +
                    ", orderPayType=" + orderPayType +
                    ", orderPayTime=" + orderPayTime +
                    ", orderPayTrading=" + orderPayTrading +
                    ", orderBespeakTime=" + orderBespeakTime +
                    ", orderDeliverMethods=" + orderDeliverMethods +
                    ", orderSettleStatus=" + orderSettleStatus +
                    ", orderPaydeliverfee=" + orderPaydeliverfee +
                    ", orderIsDelete=" + orderIsDelete +
                    ", orderSettleTime=" + orderSettleTime +
                    ", orderSettlePerson=" + orderSettlePerson +
                    ", orderRemark=" + orderRemark +
                    ", sendStatus=" + sendStatus +
                    ", business_logo='" + business_logo + '\'' +
                    ", discountMoney=" + discountMoney +
                    ", discountContent=" + discountContent +
                    ", produceType=" + produceType +
                    ", shTime=" + shTime +
                    ", businessPhone=" + businessPhone +
                    ", detailList=" + detailList +
                    ", productNames=" + productNames +
                    '}';
        }
    }


}
