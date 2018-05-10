package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hu on 2017/5/8.
 */

public class CxwyOrderInfo extends BaseEntity {

    /**
     * data : {"orderDetails":[{"orderDetailsId":163,"orderDetailsOrderNumber":"1494819465085",
     * "orderDetailsProductId":31,"orderDetailsProductName":"昂克赛拉,马自达cx5",
     * "orderDetailsBusinessPrice":101,"orderDetailsPreferentialPrice":100,
     * "orderDetailsProductNumber":1,"orderDetailsProductPrice":101,"orderDetailsProductImg":",",
     * "orderDetailsBusinessNumber":""},{"orderDetailsId":164,
     * "orderDetailsOrderNumber":"1494819465085","orderDetailsProductId":30,
     * "orderDetailsProductName":"昂克赛拉,马自达cx5","orderDetailsBusinessPrice":101,
     * "orderDetailsPreferentialPrice":100,"orderDetailsProductNumber":1,
     * "orderDetailsProductPrice":101,"orderDetailsProductImg":",",
     * "orderDetailsBusinessNumber":""}],"order":{"orderId":114,"orderBusinessName":"快快送",
     * "orderBusinessNumber":"1001","orderNumber":"1494819465085","orderUserId":2218,
     * "orderUserName":"胡智鹏","orderUserPhone":"18670819116","orderCompanyId":null,
     * "orderUserCompany":null,"orderXiangmuId":346,"orderUserXiangmu":null,
     * "orderUserAddress":"中远公馆1栋 1单元 301号","orderStatus":2,"orderOrderTime":"2017-05-15
     * 11:37:45.0","orderMoney":200,"orderSenderId":null,"orderSenderName":null,
     * "orderSenderPhone":null,"orderSendMoney":20,"orderRemark":"","orderEvaluateEvaTime":null,
     * "orderEvaluateEvaContent":null,"orderEvaluateReplyContent":null,
     * "orderEvaluateReplyTime":null,"orderEvaluateReplyPerson":null,
     * "orderEvaluateEvaLevel":null,"orderCancelRemark":null,"orderPayType":null,
     * "orderPayTime":null,"orderPayTrading":null,"orderBespeakTime":"2017-05-16 12:12:12.0"}}
     * msg : 查询成功
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
         * orderDetails : [{"orderDetailsId":163,"orderDetailsOrderNumber":"1494819465085",
         * "orderDetailsProductId":31,"orderDetailsProductName":"昂克赛拉,马自达cx5",
         * "orderDetailsBusinessPrice":101,"orderDetailsPreferentialPrice":100,
         * "orderDetailsProductNumber":1,"orderDetailsProductPrice":101,
         * "orderDetailsProductImg":",","orderDetailsBusinessNumber":""},{"orderDetailsId":164,
         * "orderDetailsOrderNumber":"1494819465085","orderDetailsProductId":30,
         * "orderDetailsProductName":"昂克赛拉,马自达cx5","orderDetailsBusinessPrice":101,
         * "orderDetailsPreferentialPrice":100,"orderDetailsProductNumber":1,
         * "orderDetailsProductPrice":101,"orderDetailsProductImg":",",
         * "orderDetailsBusinessNumber":""}]
         * order : {"orderId":114,"orderBusinessName":"快快送","orderBusinessNumber":"1001",
         * "orderNumber":"1494819465085","orderUserId":2218,"orderUserName":"胡智鹏",
         * "orderUserPhone":"18670819116","orderCompanyId":null,"orderUserCompany":null,
         * "orderXiangmuId":346,"orderUserXiangmu":null,"orderUserAddress":"中远公馆1栋 1单元 301号",
         * "orderStatus":2,"orderOrderTime":"2017-05-15 11:37:45.0","orderMoney":200,
         * "orderSenderId":null,"orderSenderName":null,"orderSenderPhone":null,
         * "orderSendMoney":20,"orderRemark":"","orderEvaluateEvaTime":null,
         * "orderEvaluateEvaContent":null,"orderEvaluateReplyContent":null,
         * "orderEvaluateReplyTime":null,"orderEvaluateReplyPerson":null,
         * "orderEvaluateEvaLevel":null,"orderCancelRemark":null,"orderPayType":null,
         * "orderPayTime":null,"orderPayTrading":null,"orderBespeakTime":"2017-05-16 12:12:12.0"}
         */

        private OrderBean order;
        private List<OrderDetailsBean> orderDetails;

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public List<OrderDetailsBean> getOrderDetails() {
            return orderDetails;
        }

        public void setOrderDetails(List<OrderDetailsBean> orderDetails) {
            this.orderDetails = orderDetails;
        }

        public static class OrderBean implements Serializable {
            /**
             * orderId : 114
             * orderBusinessName : 快快送
             * orderBusinessNumber : 1001
             * orderNumber : 1494819465085
             * orderUserId : 2218
             * orderUserName : 胡智鹏
             * orderUserPhone : 18670819116
             * orderCompanyId : null
             * orderUserCompany : null
             * orderXiangmuId : 346
             * orderUserXiangmu : null
             * orderUserAddress : 中远公馆1栋 1单元 301号
             * orderStatus : 2
             * orderOrderTime : 2017-05-15 11:37:45.0
             * orderMoney : 200.0
             * orderSenderId : null
             * orderSenderName : null
             * orderSenderPhone : null
             * orderSendMoney : 20.0
             * orderRemark :
             * orderEvaluateEvaTime : null
             * orderEvaluateEvaContent : null
             * orderEvaluateReplyContent : null
             * orderEvaluateReplyTime : null
             * orderEvaluateReplyPerson : null
             * orderEvaluateEvaLevel : null
             * orderCancelRemark : null
             * orderPayType : null
             * orderPayTime : null
             * orderPayTrading : null
             * orderBespeakTime : 2017-05-16 12:12:12.0
             * businessPhone : 商家电话
             * orderSenderPhone : 配送员电话
             */

            private int orderId;
            private String orderBusinessName;
            private String orderBusinessNumber;
            private String orderNumber;
            private int orderUserId;
            private String orderUserName;
            private String orderUserPhone;
            private Object orderCompanyId;
            private Object orderUserCompany;
            private int orderXiangmuId;
            private Object orderUserXiangmu;
            private String orderUserAddress;
            private int orderStatus;
            private String orderOrderTime;
            private double orderMoney;
            private Object orderSenderId;
            private Object orderSenderName;
            private String orderSenderPhone;
            private double orderSendMoney;
            private String orderRemark;
            private Object orderEvaluateEvaTime;
            private Object orderEvaluateEvaContent;
            private Object orderEvaluateReplyContent;
            private Object orderEvaluateReplyTime;
            private Object orderEvaluateReplyPerson;
            private Object orderEvaluateEvaLevel;
            private Object orderCancelRemark;
            private String orderPayType;//支付方式（1-微信支付；2-支付宝支付；3-银联支付；4-现金支付）
            private Object orderPayTime;
            private Object orderPayTrading;
            private String orderBespeakTime;
            private double orderFactMoney;
            private String discountContent;
            private String businessPhone;
            private double discountMoney;

            public String getBusinessPhone() {
                return businessPhone;
            }

            public void setBusinessPhone(String businessPhone) {
                this.businessPhone = businessPhone;
            }

            public String getDiscountContent() {
                return discountContent;
            }

            public void setDiscountContent(String discountContent) {
                this.discountContent = discountContent;
            }

            public double getDiscountMoney() {
                return discountMoney;
            }

            public void setDiscountMoney(double discountMoney) {
                this.discountMoney = discountMoney;
            }

            public double getOrderFactMoney() {
                return orderFactMoney;
            }

            public void setOrderFactMoney(double orderFactMoney) {
                this.orderFactMoney = orderFactMoney;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public String getOrderBusinessName() {
                return orderBusinessName;
            }

            public void setOrderBusinessName(String orderBusinessName) {
                this.orderBusinessName = orderBusinessName;
            }

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

            public int getOrderUserId() {
                return orderUserId;
            }

            public void setOrderUserId(int orderUserId) {
                this.orderUserId = orderUserId;
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

            public int getOrderXiangmuId() {
                return orderXiangmuId;
            }

            public void setOrderXiangmuId(int orderXiangmuId) {
                this.orderXiangmuId = orderXiangmuId;
            }

            public Object getOrderUserXiangmu() {
                return orderUserXiangmu;
            }

            public void setOrderUserXiangmu(Object orderUserXiangmu) {
                this.orderUserXiangmu = orderUserXiangmu;
            }

            public String getOrderUserAddress() {
                return orderUserAddress;
            }

            public void setOrderUserAddress(String orderUserAddress) {
                this.orderUserAddress = orderUserAddress;
            }

            public int getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(int orderStatus) {
                this.orderStatus = orderStatus;
            }

            public String getOrderOrderTime() {
                return orderOrderTime;
            }

            public void setOrderOrderTime(String orderOrderTime) {
                this.orderOrderTime = orderOrderTime;
            }

            public double getOrderMoney() {
                return orderMoney;
            }

            public void setOrderMoney(double orderMoney) {
                this.orderMoney = orderMoney;
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

            public String getOrderSenderPhone() {
                return orderSenderPhone;
            }

            public void setOrderSenderPhone(String orderSenderPhone) {
                this.orderSenderPhone = orderSenderPhone;
            }

            public double getOrderSendMoney() {
                return orderSendMoney;
            }

            public void setOrderSendMoney(double orderSendMoney) {
                this.orderSendMoney = orderSendMoney;
            }

            public String getOrderRemark() {
                return orderRemark;
            }

            public void setOrderRemark(String orderRemark) {
                this.orderRemark = orderRemark;
            }

            public Object getOrderEvaluateEvaTime() {
                return orderEvaluateEvaTime;
            }

            public void setOrderEvaluateEvaTime(Object orderEvaluateEvaTime) {
                this.orderEvaluateEvaTime = orderEvaluateEvaTime;
            }

            public Object getOrderEvaluateEvaContent() {
                return orderEvaluateEvaContent;
            }

            public void setOrderEvaluateEvaContent(Object orderEvaluateEvaContent) {
                this.orderEvaluateEvaContent = orderEvaluateEvaContent;
            }

            public Object getOrderEvaluateReplyContent() {
                return orderEvaluateReplyContent;
            }

            public void setOrderEvaluateReplyContent(Object orderEvaluateReplyContent) {
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

            public Object getOrderEvaluateEvaLevel() {
                return orderEvaluateEvaLevel;
            }

            public void setOrderEvaluateEvaLevel(Object orderEvaluateEvaLevel) {
                this.orderEvaluateEvaLevel = orderEvaluateEvaLevel;
            }

            public Object getOrderCancelRemark() {
                return orderCancelRemark;
            }

            public void setOrderCancelRemark(Object orderCancelRemark) {
                this.orderCancelRemark = orderCancelRemark;
            }

            public String getOrderPayType() {
                switch (orderPayType) {
                    case "1":
                        return "微信支付";
                    case "2":
                        return "支付宝支付";
                    case "3":
                        return "银联支付";
                    case "4":
                        return "现金支付";
                    default:
                        return orderPayType;
                }
//                return orderPayType;
            }

            public void setOrderPayType(String orderPayType) {
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

            public String getOrderBespeakTime() {
                return orderBespeakTime;
            }

            public void setOrderBespeakTime(String orderBespeakTime) {
                this.orderBespeakTime = orderBespeakTime;
            }
        }

        public static class OrderDetailsBean {
            /**
             * orderDetailsId : 163
             * orderDetailsOrderNumber : 1494819465085
             * orderDetailsProductId : 31
             * orderDetailsProductName : 昂克赛拉,马自达cx5
             * orderDetailsBusinessPrice : 101.0
             * orderDetailsPreferentialPrice : 100.0
             * orderDetailsProductNumber : 1
             * orderDetailsProductPrice : 101.0
             * orderDetailsProductImg : ,
             * orderDetailsBusinessNumber :
             */

            private int orderDetailsId;
            private String orderDetailsOrderNumber;
            private int orderDetailsProductId;
            private String orderDetailsProductName;
            private double orderDetailsBusinessPrice;
            private double orderDetailsPreferentialPrice;
            private int orderDetailsProductNumber;
            private double orderDetailsProductPrice;
            private String orderDetailsProductImg;
            private String orderDetailsBusinessNumber;

            public int getOrderDetailsId() {
                return orderDetailsId;
            }

            public void setOrderDetailsId(int orderDetailsId) {
                this.orderDetailsId = orderDetailsId;
            }

            public String getOrderDetailsOrderNumber() {
                return orderDetailsOrderNumber;
            }

            public void setOrderDetailsOrderNumber(String orderDetailsOrderNumber) {
                this.orderDetailsOrderNumber = orderDetailsOrderNumber;
            }

            public int getOrderDetailsProductId() {
                return orderDetailsProductId;
            }

            public void setOrderDetailsProductId(int orderDetailsProductId) {
                this.orderDetailsProductId = orderDetailsProductId;
            }

            public String getOrderDetailsProductName() {
                return orderDetailsProductName;
            }

            public void setOrderDetailsProductName(String orderDetailsProductName) {
                this.orderDetailsProductName = orderDetailsProductName;
            }

            public double getOrderDetailsBusinessPrice() {
                return orderDetailsBusinessPrice;
            }

            public void setOrderDetailsBusinessPrice(double orderDetailsBusinessPrice) {
                this.orderDetailsBusinessPrice = orderDetailsBusinessPrice;
            }

            public double getOrderDetailsPreferentialPrice() {
                return orderDetailsPreferentialPrice;
            }

            public void setOrderDetailsPreferentialPrice(double orderDetailsPreferentialPrice) {
                this.orderDetailsPreferentialPrice = orderDetailsPreferentialPrice;
            }

            public int getOrderDetailsProductNumber() {
                return orderDetailsProductNumber;
            }

            public void setOrderDetailsProductNumber(int orderDetailsProductNumber) {
                this.orderDetailsProductNumber = orderDetailsProductNumber;
            }

            public double getOrderDetailsProductPrice() {
                return orderDetailsProductPrice;
            }

            public void setOrderDetailsProductPrice(double orderDetailsProductPrice) {
                this.orderDetailsProductPrice = orderDetailsProductPrice;
            }

            public String getOrderDetailsProductImg() {
                return orderDetailsProductImg;
            }

            public void setOrderDetailsProductImg(String orderDetailsProductImg) {
                this.orderDetailsProductImg = orderDetailsProductImg;
            }

            public String getOrderDetailsBusinessNumber() {
                return orderDetailsBusinessNumber;
            }

            public void setOrderDetailsBusinessNumber(String orderDetailsBusinessNumber) {
                this.orderDetailsBusinessNumber = orderDetailsBusinessNumber;
            }
        }
    }
}
