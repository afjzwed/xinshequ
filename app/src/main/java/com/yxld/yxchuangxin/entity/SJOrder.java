package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 订单Entity
 *
 * @author BOOM
 */
@SuppressWarnings("serial")
public class SJOrder extends BaseEntity implements Serializable {

    /**
     * 状态码
     */
    public String success;

    private int total;

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

    private List<SJOrder> data;

    private Integer orderId;
    private String orderBusinessName;// '商家名称',
    private String orderBusinessNumber;// '商家序列号',
    private String orderNumber;// '订单编号（序列号）',
    private Integer orderUserId;// '用户编号',
    private String orderUserName;// '用户姓名',
    private String orderUserPhone;// '用户电话',
    private Integer orderCompanyId;// '公司编号',
    private String orderUserCompany;// '用户所属公司',
    private Integer orderXiangmuId;// '项目编号',
    private String orderUserXiangmu;// '用户所属项目',
    private String orderUserAddress;// '用户收货地址',
    private Integer orderStatus;// '订单状态（1-待付款；2-待接单；3-待发货；4-待确认；5-待评价；6-订单完成；7-待回复；8-已拒单；9
    // -取消订单；10-待取件；11-清洗中；12-送还中；13-退款中；）',
    private String orderOrderTime;// '下单时间',
    private Double orderMoney;// '订单总金额',
    private String orderSenderId;// '配送人编号',
    private String orderSenderName;// '配送人姓名',
    private String orderSenderPhone;// '配送人电话',
    private Double orderSendMoney;// '配送费',
    private String orderRemark;// '备注',留言
    private String orderEvaluateEvaTime;// '评价时间',
    private String orderEvaluateEvaContent;// '评价内容',
    private String orderEvaluateReplyContent;// 回复评价
    private String orderEvaluateReplyTime;// 回复时间
    private String orderEvaluateReplyPerson;// 评价人
    private Integer orderEvaluateEvaLevel;// '评价等级（1-1星；2-2星；3-3星；4-4星；5-5星）',
    private String orderCancelRemark;//		取消订单原因
    private String orderPayType;//		支付方式
    private String orderPayTime;//	支付时间
    private String orderPayTrading;//		支付交易号

    private Integer orderDeliverMethods;//配送方式：(1.商家承运 2.平台承运) 12.21William新增
    private String orderBespeakTime;//预约取件时间 12.21William新增
    private Integer orderPaydeliverfee;//配送费承担(1.客户承担 2.商家承担) 12.21William新增
    private Double orderFactMoney;//实付金额 12.21William新增
    private Double orderSettleMoney;//结算金额 12.21William新增
    private String business_logo;//商家logo字段 12.26William新增

    //以下为1.19William新增字段
    private int orderSettleStatus;//结算状态  1 已结算  2 未结算
    private int  orderIsDelete;//删除标识（1:已删除;-1:未删除）
    private String  orderSettleTime;//结算时间
    private String orderSettlePerson;//结算人
    private int  sendStatus;//是否需要安排配送，-1是不需要，1是需要，物业配送，配送人为空则需要，配送人不为空则不需要
    private int  discountMoney;//优惠活动金额
    private String  discountContent;// 活动内容
    private int produceType;//产品类别（1-商品；2-服务）
    private String shTime;//待送还时间
    private String  businessPhone;//商家手机号
    private Object detailList;// List<SJOrderDetails> 订单详情 可以用CxwyOrderInfo内部类OrderDetailsBean
    private String productNames;// 商品名等

    public String getProductNames() {
        return productNames;
    }

    public void setProductNames(String productNames) {
        this.productNames = productNames;
    }

    public List<SJOrder> getData() {
        return data;
    }

    public void setData(List<SJOrder> data) {
        this.data = data;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
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

    public Integer getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(Integer orderUserId) {
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

    public Integer getOrderCompanyId() {
        return orderCompanyId;
    }

    public void setOrderCompanyId(Integer orderCompanyId) {
        this.orderCompanyId = orderCompanyId;
    }

    public String getOrderUserCompany() {
        return orderUserCompany;
    }

    public void setOrderUserCompany(String orderUserCompany) {
        this.orderUserCompany = orderUserCompany;
    }

    public Integer getOrderXiangmuId() {
        return orderXiangmuId;
    }

    public void setOrderXiangmuId(Integer orderXiangmuId) {
        this.orderXiangmuId = orderXiangmuId;
    }

    public String getOrderUserXiangmu() {
        return orderUserXiangmu;
    }

    public void setOrderUserXiangmu(String orderUserXiangmu) {
        this.orderUserXiangmu = orderUserXiangmu;
    }

    public String getOrderUserAddress() {
        return orderUserAddress;
    }

    public void setOrderUserAddress(String orderUserAddress) {
        this.orderUserAddress = orderUserAddress;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderOrderTime() {
        return orderOrderTime;
    }

    public void setOrderOrderTime(String orderOrderTime) {
        this.orderOrderTime = orderOrderTime;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getOrderSenderId() {
        return orderSenderId;
    }

    public void setOrderSenderId(String orderSenderId) {
        this.orderSenderId = orderSenderId;
    }

    public String getOrderSenderName() {
        return orderSenderName;
    }

    public void setOrderSenderName(String orderSenderName) {
        this.orderSenderName = orderSenderName;
    }

    public String getOrderSenderPhone() {
        return orderSenderPhone;
    }

    public void setOrderSenderPhone(String orderSenderPhone) {
        this.orderSenderPhone = orderSenderPhone;
    }

    public Double getOrderSendMoney() {
        return orderSendMoney;
    }

    public void setOrderSendMoney(Double orderSendMoney) {
        this.orderSendMoney = orderSendMoney;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
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

    public Integer getOrderEvaluateEvaLevel() {
        return orderEvaluateEvaLevel;
    }

    public void setOrderEvaluateEvaLevel(Integer orderEvaluateEvaLevel) {
        this.orderEvaluateEvaLevel = orderEvaluateEvaLevel;
    }

    public String getOrderCancelRemark() {
        return orderCancelRemark;
    }

    public void setOrderCancelRemark(String orderCancelRemark) {
        this.orderCancelRemark = orderCancelRemark;
    }

    public String getOrderPayType() {
        return orderPayType;
    }

    public void setOrderPayType(String orderPayType) {
        this.orderPayType = orderPayType;
    }

    public String getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(String orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public String getOrderPayTrading() {
        return orderPayTrading;
    }

    public void setOrderPayTrading(String orderPayTrading) {
        this.orderPayTrading = orderPayTrading;
    }

    public Integer getOrderDeliverMethods() {
        return orderDeliverMethods;
    }

    public void setOrderDeliverMethods(Integer orderDeliverMethods) {
        this.orderDeliverMethods = orderDeliverMethods;
    }

    public String getOrderBespeakTime() {
        return orderBespeakTime;
    }

    public void setOrderBespeakTime(String orderBespeakTime) {
        this.orderBespeakTime = orderBespeakTime;
    }

    public Integer getOrderPaydeliverfee() {
        return orderPaydeliverfee;
    }

    public void setOrderPaydeliverfee(Integer orderPaydeliverfee) {
        this.orderPaydeliverfee = orderPaydeliverfee;
    }

    public Double getOrderFactMoney() {
        return orderFactMoney;
    }

    public void setOrderFactMoney(Double orderFactMoney) {
        this.orderFactMoney = orderFactMoney;
    }

    public Double getOrderSettleMoney() {
        return orderSettleMoney;
    }

    public void setOrderSettleMoney(Double orderSettleMoney) {
        this.orderSettleMoney = orderSettleMoney;
    }

    public String getBusinessLogo() {
        return business_logo;
    }

    public void setBusinessLogo(String business_logo) {
        this.business_logo = business_logo;
    }

    public String getBusiness_logo() {
        return business_logo;
    }

    public void setBusiness_logo(String business_logo) {
        this.business_logo = business_logo;
    }

    public int getOrderSettleStatus() {
        return orderSettleStatus;
    }

    public void setOrderSettleStatus(int orderSettleStatus) {
        this.orderSettleStatus = orderSettleStatus;
    }

    public int getOrderIsDelete() {
        return orderIsDelete;
    }

    public void setOrderIsDelete(int orderIsDelete) {
        this.orderIsDelete = orderIsDelete;
    }

    public String getOrderSettleTime() {
        return orderSettleTime;
    }

    public void setOrderSettleTime(String orderSettleTime) {
        this.orderSettleTime = orderSettleTime;
    }

    public String getOrderSettlePerson() {
        return orderSettlePerson;
    }

    public void setOrderSettlePerson(String orderSettlePerson) {
        this.orderSettlePerson = orderSettlePerson;
    }

    public int getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(int sendStatus) {
        this.sendStatus = sendStatus;
    }

    public int getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(int discountMoney) {
        this.discountMoney = discountMoney;
    }

    public String getDiscountContent() {
        return discountContent;
    }

    public void setDiscountContent(String discountContent) {
        this.discountContent = discountContent;
    }

    public int getProduceType() {
        return produceType;
    }

    public void setProduceType(int produceType) {
        this.produceType = produceType;
    }

    public String getShTime() {
        return shTime;
    }

    public void setShTime(String shTime) {
        this.shTime = shTime;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public Object getDetailList() {
        return detailList;
    }

    public void setDetailList(Object detailList) {
        this.detailList = detailList;
    }

    @Override
    public String toString() {
        return "SJOrder{" +
                "success='" + success + '\'' +
                ", total=" + total +
                ", data=" + data +
                ", orderId=" + orderId +
                ", orderBusinessName='" + orderBusinessName + '\'' +
                ", orderBusinessNumber='" + orderBusinessNumber + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderUserId=" + orderUserId +
                ", orderUserName='" + orderUserName + '\'' +
                ", orderUserPhone='" + orderUserPhone + '\'' +
                ", orderCompanyId=" + orderCompanyId +
                ", orderUserCompany='" + orderUserCompany + '\'' +
                ", orderXiangmuId=" + orderXiangmuId +
                ", orderUserXiangmu='" + orderUserXiangmu + '\'' +
                ", orderUserAddress='" + orderUserAddress + '\'' +
                ", orderStatus=" + orderStatus +
                ", orderOrderTime='" + orderOrderTime + '\'' +
                ", orderMoney=" + orderMoney +
                ", orderSenderId='" + orderSenderId + '\'' +
                ", orderSenderName='" + orderSenderName + '\'' +
                ", orderSenderPhone='" + orderSenderPhone + '\'' +
                ", orderSendMoney=" + orderSendMoney +
                ", orderRemark='" + orderRemark + '\'' +
                ", orderEvaluateEvaTime='" + orderEvaluateEvaTime + '\'' +
                ", orderEvaluateEvaContent='" + orderEvaluateEvaContent + '\'' +
                ", orderEvaluateReplyContent='" + orderEvaluateReplyContent + '\'' +
                ", orderEvaluateReplyTime='" + orderEvaluateReplyTime + '\'' +
                ", orderEvaluateReplyPerson='" + orderEvaluateReplyPerson + '\'' +
                ", orderEvaluateEvaLevel=" + orderEvaluateEvaLevel +
                ", orderCancelRemark='" + orderCancelRemark + '\'' +
                ", orderPayType='" + orderPayType + '\'' +
                ", orderPayTime='" + orderPayTime + '\'' +
                ", orderPayTrading='" + orderPayTrading + '\'' +
                ", orderDeliverMethods=" + orderDeliverMethods +
                ", orderBespeakTime='" + orderBespeakTime + '\'' +
                ", orderPaydeliverfee=" + orderPaydeliverfee +
                ", orderFactMoney=" + orderFactMoney +
                ", orderSettleMoney=" + orderSettleMoney +
                ", business_logo='" + business_logo + '\'' +
                ", orderSettleStatus=" + orderSettleStatus +
                ", orderIsDelete=" + orderIsDelete +
                ", orderSettleTime='" + orderSettleTime + '\'' +
                ", orderSettlePerson='" + orderSettlePerson + '\'' +
                ", sendStatus=" + sendStatus +
                ", discountMoney=" + discountMoney +
                ", discountContent='" + discountContent + '\'' +
                ", produceType=" + produceType +
                ", shTime='" + shTime + '\'' +
                ", businessPhone='" + businessPhone + '\'' +
                ", detailList=" + detailList +
                '}';
    }
}
