package com.yxld.yxchuangxin.entity;
////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//         佛祖保佑       永无BUG     永不修改                  //
////////////////////////////////////////////////////////////////////

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * Created by 89876 on 2017/4/24 0024.
 * 个人主页：http://yishangfei.me
 * Github:https://github.com/yishangfei
 * <p>
 * 欣周边 商家详情实体
 */
public class CxwyBusiness extends BaseEntity {

    /**
     * data : [{"score":3,"business":{"businessId":20,"businessNumber":"1001","businessCompany":"ld","businessCompanyId":1,"businessProjectName":"","businessProjectId":0,"businessName":"快快送","businessLogo":"","businessAddress":"娄底","businessProperty":0,"businessPersonInCharge":"","businessPhone":"18888888888","businessProduceType":0,"businessPlatformBonus":0,"businessCompanyBonus":0,"businessJoinTime":"2017-05-05 23:59:59.0","businessStatus":1,"businessDetails":"无","businessServerIntroduce":""},"activity":[{"activityId":7,"activityBusinessNumber":"1001","activityName":"放肆","activityStartTime":"2017-04-28 10:10:10.0","activityEndTime":"2017-05-20 00:00:00.0","activityExplain":"放肆嗨","activityShare":null,"activityStatus":1},{"activityId":11,"activityBusinessNumber":"1001","activityName":"孤独求败 ","activityStartTime":"2017-04-28 10:10:10.0","activityEndTime":"2017-05-20 00:00:00.0","activityExplain":"孤独求败 ","activityShare":null,"activityStatus":1},{"activityId":15,"activityBusinessNumber":"1001","activityName":"五一总","activityStartTime":"2017-04-22 19:05:11.0","activityEndTime":"2017-05-20 00:00:00.0","activityExplain":"五一","activityShare":null,"activityStatus":1}]}]
     * msg : 查询成功成功
     * success : 1
     * total : 19
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
         * score : 3
         * business : {"businessId":20,"businessNumber":"1001","businessCompany":"ld","businessCompanyId":1,"businessProjectName":"","businessProjectId":0,"businessName":"快快送","businessLogo":"","businessAddress":"娄底","businessProperty":0,"businessPersonInCharge":"","businessPhone":"18888888888","businessProduceType":0,"businessPlatformBonus":0,"businessCompanyBonus":0,"businessJoinTime":"2017-05-05 23:59:59.0","businessStatus":1,"businessDetails":"无","businessServerIntroduce":""}
         * activity : [{"activityId":7,"activityBusinessNumber":"1001","activityName":"放肆","activityStartTime":"2017-04-28 10:10:10.0","activityEndTime":"2017-05-20 00:00:00.0","activityExplain":"放肆嗨","activityShare":null,"activityStatus":1},{"activityId":11,"activityBusinessNumber":"1001","activityName":"孤独求败 ","activityStartTime":"2017-04-28 10:10:10.0","activityEndTime":"2017-05-20 00:00:00.0","activityExplain":"孤独求败 ","activityShare":null,"activityStatus":1},{"activityId":15,"activityBusinessNumber":"1001","activityName":"五一总","activityStartTime":"2017-04-22 19:05:11.0","activityEndTime":"2017-05-20 00:00:00.0","activityExplain":"五一","activityShare":null,"activityStatus":1}]
         */

        private String score;
        private BusinessBean business;
        private List<ActivityBean> activity;


        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public BusinessBean getBusiness() {
            return business;
        }

        public void setBusiness(BusinessBean business) {
            this.business = business;
        }

        public List<ActivityBean> getActivity() {
            return activity;
        }

        public void setActivity(List<ActivityBean> activity) {
            this.activity = activity;
        }

        public static class BusinessBean {
            /**
             * businessId : 20
             * businessNumber : 1001
             * businessCompany : ld
             * businessCompanyId : 1
             * businessProjectName :
             * businessProjectId : 0
             * businessName : 快快送
             * businessLogo :
             * businessAddress : 娄底
             * businessProperty : 0
             * businessPersonInCharge :
             * businessPhone : 18888888888
             * businessProduceType : 1 2代表服务 1代表商家
             * businessPlatformBonus : 0.0
             * businessCompanyBonus : 0.0
             * businessJoinTime : 2017-05-05 23:59:59.0
             * businessStatus : 1
             * businessDetails : 无
             * businessServerIntroduce :
             */

            private int businessId;
            private String businessNumber;
            private String businessCompany;
            private int businessCompanyId;
            private String businessProjectName;
            private int businessProjectId;
            private String businessName;
            private String businessLogo;
            private String businessAddress;
            private int businessProperty;
            private String businessPersonInCharge;
            private String businessPhone;
            private int businessProduceType;
            private double businessPlatformBonus;
            private double businessCompanyBonus;
            private String businessJoinTime;
            private int businessStatus;
            private String businessDetails;
            private String businessServerIntroduce;

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

            public double getBusinessPlatformBonus() {
                return businessPlatformBonus;
            }

            public void setBusinessPlatformBonus(double businessPlatformBonus) {
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
