package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * Created by William on 2017/12/19.
 */

public class RimShopDetailBean extends BaseEntity {

    /**
     * data : {"score":5,"business":{"businessId":3,"businessNumber":"1003",
     * "businessCompany":"创欣物业","businessCompanyId":1,"businessProjectName":"中远公馆",
     * "businessProjectId":346,"businessName":"三盐两语","businessLogo":"upload/img/1510126468656",
     * "businessAddress":"湖南长沙","businessProperty":4,"businessPersonInCharge":"侯兰",
     * "businessPhone":"18711111111","businessProduceType":1,"businessPlatformBonus":0,
     * "businessCompanyBonus":0.15,"businessJoinTime":"2017-05-03 23:59:59.0","businessStatus":1,
     * "businessDetails":"http://image.17173.com/bbs/v1/2010/10/13/1286952850323.jpg;
     * 大是大非df4s9848910f大的","businessServerIntroduce":null,"sellOrderNum":null},"activity":[],
     * "pingjia":[{"orderBusinessNumber":"1003","orderNumber":"002184sj1513302245922",
     * "orderUserName":"庾亮","orderUserPhone":"15575125570","orderEvaluateEvaTime":"2017-12-15
     * 10:29:24.0","orderEvaluateEvaContent":"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈好哈哈哈哈说说话哈哈哈哈哈哈哈哈哈哈",
     * "orderEvaluateEvaLevel":5,"orderEvaluateReplyContent":null,"orderEvaluateReplyTime":null,
     * "orderEvaluateReplyPerson":null}],"appraiseNum":1,"orderNum":9}
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
         * score : 5
         * business : {"businessId":3,"businessNumber":"1003","businessCompany":"创欣物业",
         * "businessCompanyId":1,"businessProjectName":"中远公馆","businessProjectId":346,
         * "businessName":"三盐两语","businessLogo":"upload/img/1510126468656",
         * "businessAddress":"湖南长沙","businessProperty":4,"businessPersonInCharge":"侯兰",
         * "businessPhone":"18711111111","businessProduceType":1,"businessPlatformBonus":0,
         * "businessCompanyBonus":0.15,"businessJoinTime":"2017-05-03 23:59:59.0",
         * "businessStatus":1,"businessDetails":"http://image.17173.com/bbs/v1/2010/10/13
         * /1286952850323.jpg;大是大非df4s9848910f大的","businessServerIntroduce":null,
         * "sellOrderNum":null}
         * activity : []
         * pingjia : [{"orderBusinessNumber":"1003","orderNumber":"002184sj1513302245922",
         * "orderUserName":"庾亮","orderUserPhone":"15575125570","orderEvaluateEvaTime":"2017-12-15
         * 10:29:24.0","orderEvaluateEvaContent":"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈好哈哈哈哈说说话哈哈哈哈哈哈哈哈哈哈",
         * "orderEvaluateEvaLevel":5,"orderEvaluateReplyContent":null,
         * "orderEvaluateReplyTime":null,"orderEvaluateReplyPerson":null}]
         * appraiseNum : 1
         * orderNum : 9
         */

        private double score;
        private BusinessBean business;
        private int appraiseNum;
        private int orderNum;
        private List<ActivityBean> activity;
        private List<PingjiaBean> pingjia;
        private String content;


        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public BusinessBean getBusiness() {
            return business;
        }

        public void setBusiness(BusinessBean business) {
            this.business = business;
        }

        public int getAppraiseNum() {
            return appraiseNum;
        }

        public void setAppraiseNum(int appraiseNum) {
            this.appraiseNum = appraiseNum;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public List<ActivityBean> getActivity() {
            return activity;
        }

        public void setActivity(List<ActivityBean> activity) {
            this.activity = activity;
        }

        public List<PingjiaBean> getPingjia() {
            return pingjia;
        }

        public void setPingjia(List<PingjiaBean> pingjia) {
            this.pingjia = pingjia;
        }

        public static class BusinessBean {
            /**
             * businessId : 3
             * businessNumber : 1003
             * businessCompany : 创欣物业
             * businessCompanyId : 1
             * businessProjectName : 中远公馆
             * businessProjectId : 346
             * businessName : 三盐两语
             * businessLogo : upload/img/1510126468656
             * businessAddress : 湖南长沙
             * businessProperty : 4
             * businessPersonInCharge : 侯兰
             * businessPhone : 18711111111
             * businessProduceType : 1
             * businessPlatformBonus : 0
             * businessCompanyBonus : 0.15
             * businessJoinTime : 2017-05-03 23:59:59.0
             * businessStatus : 1
             * businessDetails : http://image.17173.com/bbs/v1/2010/10/13/1286952850323.jpg;
             * 大是大非df4s9848910f大的
             * businessServerIntroduce : null
             * sellOrderNum : null
             */

            private int businessId;//	商家编号
            private String businessNumber;//	商家序列号
            private String businessCompany;//	所属公司名称
            private int businessCompanyId;//	公司ID
            private String businessProjectName;//	所属项目名称
            private int businessProjectId;//	项目ID
            private String businessName;//	商家名称
            private String businessLogo;//	商家LOGO
            private String businessAddress;//	商家地址
            private int businessProperty;//	商家性质（1-平台自营；2-平台联营；3-物业自营；4-物业联营；5-商家自营）
            private String businessPersonInCharge;//	商家负责人
            private String businessPhone;//	商家电话
            private int businessProduceType;//	产品类别（1-商品；2-服务）
            private int businessPlatformBonus;//	平台分成百分比
            private double businessCompanyBonus;//	公司分成百分比
            private String businessJoinTime;//	入驻时间
            private int businessStatus;//	商家状态（-1-禁用；1-营业中；2-休业中）
            private String businessDetails;//	商家介绍
            private String businessServerIntroduce;//	服务介绍
            private int sellOrderNum;/*销售的已付款的订单数量*/

            public int getBusinessId() {
                return businessId;
            }

            public void setBusinessId(int businessId) {
                this.businessId = businessId;
            }

            public String getBusinessNumber() {
                return businessNumber;
            }

            public void setBusinessNumber(String businessNumber) {
                this.businessNumber = businessNumber;
            }

            public String getBusinessCompany() {
                return businessCompany;
            }

            public void setBusinessCompany(String businessCompany) {
                this.businessCompany = businessCompany;
            }

            public int getBusinessCompanyId() {
                return businessCompanyId;
            }

            public void setBusinessCompanyId(int businessCompanyId) {
                this.businessCompanyId = businessCompanyId;
            }

            public String getBusinessProjectName() {
                return businessProjectName;
            }

            public void setBusinessProjectName(String businessProjectName) {
                this.businessProjectName = businessProjectName;
            }

            public int getBusinessProjectId() {
                return businessProjectId;
            }

            public void setBusinessProjectId(int businessProjectId) {
                this.businessProjectId = businessProjectId;
            }

            public String getBusinessName() {
                return businessName;
            }

            public void setBusinessName(String businessName) {
                this.businessName = businessName;
            }

            public String getBusinessLogo() {
                return businessLogo;
            }

            public void setBusinessLogo(String businessLogo) {
                this.businessLogo = businessLogo;
            }

            public String getBusinessAddress() {
                return businessAddress;
            }

            public void setBusinessAddress(String businessAddress) {
                this.businessAddress = businessAddress;
            }

            public int getBusinessProperty() {
                return businessProperty;
            }

            public void setBusinessProperty(int businessProperty) {
                this.businessProperty = businessProperty;
            }

            public String getBusinessPersonInCharge() {
                return businessPersonInCharge;
            }

            public void setBusinessPersonInCharge(String businessPersonInCharge) {
                this.businessPersonInCharge = businessPersonInCharge;
            }

            public String getBusinessPhone() {
                return businessPhone;
            }

            public void setBusinessPhone(String businessPhone) {
                this.businessPhone = businessPhone;
            }

            public int getBusinessProduceType() {
                return businessProduceType;
            }

            public void setBusinessProduceType(int businessProduceType) {
                this.businessProduceType = businessProduceType;
            }

            public int getBusinessPlatformBonus() {
                return businessPlatformBonus;
            }

            public void setBusinessPlatformBonus(int businessPlatformBonus) {
                this.businessPlatformBonus = businessPlatformBonus;
            }

            public double getBusinessCompanyBonus() {
                return businessCompanyBonus;
            }

            public void setBusinessCompanyBonus(double businessCompanyBonus) {
                this.businessCompanyBonus = businessCompanyBonus;
            }

            public String getBusinessJoinTime() {
                return businessJoinTime;
            }

            public void setBusinessJoinTime(String businessJoinTime) {
                this.businessJoinTime = businessJoinTime;
            }

            public int getBusinessStatus() {
                return businessStatus;
            }

            public void setBusinessStatus(int businessStatus) {
                this.businessStatus = businessStatus;
            }

            public String getBusinessDetails() {
                return businessDetails;
            }

            public void setBusinessDetails(String businessDetails) {
                this.businessDetails = businessDetails;
            }

            public String getBusinessServerIntroduce() {
                return businessServerIntroduce;
            }

            public void setBusinessServerIntroduce(String businessServerIntroduce) {
                this.businessServerIntroduce = businessServerIntroduce;
            }

            public int getSellOrderNum() {
                return sellOrderNum;
            }

            public void setSellOrderNum(int sellOrderNum) {
                this.sellOrderNum = sellOrderNum;
            }
        }

        public static class PingjiaBean {
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

            private String orderBusinessNumber;//	商家序列号
            private String orderNumber;// '订单编号（序列号）
            private String orderUserName;// '用户姓名',
            private String orderUserPhone;// '用户电话'
            private String orderEvaluateEvaTime;// '评价时间',
            private String orderEvaluateEvaContent;// '评价内容',
            private int orderEvaluateEvaLevel;// '评价等级（1-1星；2-2星；3-3星；4-4星；5-5星）',
            private String orderEvaluateReplyContent;// 回复评价
            private String orderEvaluateReplyTime;// 回复时间
            private String orderEvaluateReplyPerson;// 回复人

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
        }

        public static class ActivityBean {
            /**
             * activityId : 7
             * activityBusinessNumber : 1001
             * activityName : 放肆
             * activityStartTime : 2017-04-28 10:10:10.0
             * activityEndTime : 2017-05-20 00:00:00.0
             * activityExplain : 放肆嗨
             * activityShare : null
             * activityStatus : 1
             */

            private int activityId;
            private String activityBusinessNumber;
            private String activityName;
            private String activityStartTime;
            private String activityEndTime;
            private String activityExplain;
            private Object activityShare;
            private int activityStatus;

            public int getActivityId() {
                return activityId;
            }

            public void setActivityId(int activityId) {
                this.activityId = activityId;
            }

            public String getActivityBusinessNumber() {
                return activityBusinessNumber;
            }

            public void setActivityBusinessNumber(String activityBusinessNumber) {
                this.activityBusinessNumber = activityBusinessNumber;
            }

            public String getActivityName() {
                return activityName;
            }

            public void setActivityName(String activityName) {
                this.activityName = activityName;
            }

            public String getActivityStartTime() {
                return activityStartTime;
            }

            public void setActivityStartTime(String activityStartTime) {
                this.activityStartTime = activityStartTime;
            }

            public String getActivityEndTime() {
                return activityEndTime;
            }

            public void setActivityEndTime(String activityEndTime) {
                this.activityEndTime = activityEndTime;
            }

            public String getActivityExplain() {
                return activityExplain;
            }

            public void setActivityExplain(String activityExplain) {
                this.activityExplain = activityExplain;
            }

            public Object getActivityShare() {
                return activityShare;
            }

            public void setActivityShare(Object activityShare) {
                this.activityShare = activityShare;
            }

            public int getActivityStatus() {
                return activityStatus;
            }

            public void setActivityStatus(int activityStatus) {
                this.activityStatus = activityStatus;
            }
        }
    }
}
