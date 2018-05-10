package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * 欣周边 提交预约折扣实体类
 * Created by William on 2017/12/20.
 */

public class RimActivityDiscount extends BaseEntity {

    /**
     * data : {"activityContent":"全场大促销 ","discountMoney":5,"delivers":{"ruleId":null,
     * "companyId":null,"projectId":null,"businessNumber":"1001","deliverMethods":2,
     * "chargeMode":null,"chargeStandard":null,"distributionFee":2,"startMoney":10,
     * "freeMoney":null}}
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
         * activityContent : 全场大促销
         * discountMoney : 5
         * delivers : {"ruleId":null,"companyId":null,"projectId":null,"businessNumber":"1001",
         * "deliverMethods":2,"chargeMode":null,"chargeStandard":null,"distributionFee":2,
         * "startMoney":10,"freeMoney":null}
         */

        private String activityContent;//优惠活动
        private int discountMoney;//总折扣价
        private List<DeliversBean> delivers;

        public String getActivityContent() {
            return activityContent;
        }

        public void setActivityContent(String activityContent) {
            this.activityContent = activityContent;
        }

        public int getDiscountMoney() {
            return discountMoney;
        }

        public void setDiscountMoney(int discountMoney) {
            this.discountMoney = discountMoney;
        }

        public List<DeliversBean> getDelivers() {
            return delivers;
        }

        public void setDelivers(List<DeliversBean> delivers) {
            this.delivers = delivers;
        }

        public static class DeliversBean {
            /**
             * ruleId : null
             * companyId : null
             * projectId : null
             * businessNumber : 1001
             * deliverMethods : 2
             * chargeMode : null
             * chargeStandard : null
             * distributionFee : 2
             * startMoney : 10
             * freeMoney : null
             * orderPaydeliverfee:
             */
            private int orderPaydeliverfee;
            private int ruleId;
            private int companyId;
            private int projectId;
            private String businessNumber;//商家序列号
            private int deliverMethods;//配送方式 (1.商家承运 2.平台承运)
            private int chargeMode;//结算配送费方式
            private double chargeStandard;//收费标准
            private double distributionFee;//配送费
            private double startMoney;//起送金额
            private double freeMoney;

            public int getOrderPaydeliverfee() {
                return orderPaydeliverfee;
            }

            public void setOrderPaydeliverfee(int orderPaydeliverfee) {
                this.orderPaydeliverfee = orderPaydeliverfee;
            }

            public int getRuleId() {
                return ruleId;
            }

            public void setRuleId(int ruleId) {
                this.ruleId = ruleId;
            }

            public int getCompanyId() {
                return companyId;
            }

            public void setCompanyId(int companyId) {
                this.companyId = companyId;
            }

            public int getProjectId() {
                return projectId;
            }

            public void setProjectId(int projectId) {
                this.projectId = projectId;
            }

            public String getBusinessNumber() {
                return businessNumber;
            }

            public void setBusinessNumber(String businessNumber) {
                this.businessNumber = businessNumber;
            }

            public int getDeliverMethods() {
                return deliverMethods;
            }

            public void setDeliverMethods(int deliverMethods) {
                this.deliverMethods = deliverMethods;
            }

            public int getChargeMode() {
                return chargeMode;
            }

            public void setChargeMode(int chargeMode) {
                this.chargeMode = chargeMode;
            }

            public double getChargeStandard() {
                return chargeStandard;
            }

            public void setChargeStandard(double chargeStandard) {
                this.chargeStandard = chargeStandard;
            }

            public double getDistributionFee() {
                return distributionFee;
            }

            public void setDistributionFee(double distributionFee) {
                this.distributionFee = distributionFee;
            }

            public double getStartMoney() {
                return startMoney;
            }

            public void setStartMoney(double startMoney) {
                this.startMoney = startMoney;
            }

            public double getFreeMoney() {
                return freeMoney;
            }

            public void setFreeMoney(double freeMoney) {
                this.freeMoney = freeMoney;
            }

            @Override
            public String toString() {
                return "DeliversBean{" +
                        "ruleId=" + ruleId +
                        ", companyId=" + companyId +
                        ", projectId=" + projectId +
                        ", businessNumber='" + businessNumber + '\'' +
                        ", deliverMethods=" + deliverMethods +
                        ", chargeMode=" + chargeMode +
                        ", chargeStandard=" + chargeStandard +
                        ", distributionFee=" + distributionFee +
                        ", startMoney=" + startMoney +
                        ", freeMoney=" + freeMoney +
                        '}';
            }
        }
    }
}
