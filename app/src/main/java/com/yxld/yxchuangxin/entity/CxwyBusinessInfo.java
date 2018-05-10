package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hu on 2017/5/3.
 */

public class CxwyBusinessInfo extends BaseEntity {

    /**
     * data : {"score":3,"business":{"businessId":20,"businessNumber":"1001","businessCompany":"ld","businessCompanyId":1,"businessProjectName":"","businessProjectId":0,"businessName":"快快送","businessLogo":"","businessAddress":"娄底","businessProperty":0,"businessPersonInCharge":"","businessPhone":"18888888888","businessProduceType":0,"businessPlatformBonus":0,"businessCompanyBonus":0,"businessJoinTime":"2017-05-05 23:59:59.0","businessStatus":1,"businessDetails":"无","businessServerIntroduce":""},"activity":[{"activityId":7,"activityBusinessNumber":"1001","activityName":"放肆","activityStartTime":"2017-04-28 10:10:10.0","activityEndTime":"2017-05-20 00:00:00.0","activityExplain":"放肆嗨","activityShare":null,"activityStatus":1},{"activityId":11,"activityBusinessNumber":"1001","activityName":"孤独求败 ","activityStartTime":"2017-04-28 10:10:10.0","activityEndTime":"2017-05-20 00:00:00.0","activityExplain":"孤独求败 ","activityShare":null,"activityStatus":1},{"activityId":15,"activityBusinessNumber":"1001","activityName":"五一总","activityStartTime":"2017-04-22 19:05:11.0","activityEndTime":"2017-05-20 00:00:00.0","activityExplain":"五一","activityShare":null,"activityStatus":1}],"productClassify":[{"classifyId":2,"classifyName":"视频食品","classifyHigherId":0,"classifyLevel":1,"classifyDisplay":1,"classifyBusinessNumber":"1001"},{"classifyId":3,"classifyName":"饮品","classifyHigherId":0,"classifyLevel":1,"classifyDisplay":1,"classifyBusinessNumber":"1001"},{"classifyId":10,"classifyName":"冰淇淋","classifyHigherId":0,"classifyLevel":1,"classifyDisplay":1,"classifyBusinessNumber":"1001"}]}
     * msg : 查询成功成功
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
         * score : 3
         * business : {"businessId":20,"businessNumber":"1001","businessCompany":"ld","businessCompanyId":1,"businessProjectName":"","businessProjectId":0,"businessName":"快快送","businessLogo":"","businessAddress":"娄底","businessProperty":0,"businessPersonInCharge":"","businessPhone":"18888888888","businessProduceType":0,"businessPlatformBonus":0,"businessCompanyBonus":0,"businessJoinTime":"2017-05-05 23:59:59.0","businessStatus":1,"businessDetails":"无","businessServerIntroduce":""}
         * activity : [{"activityId":7,"activityBusinessNumber":"1001","activityName":"放肆","activityStartTime":"2017-04-28 10:10:10.0","activityEndTime":"2017-05-20 00:00:00.0","activityExplain":"放肆嗨","activityShare":null,"activityStatus":1},{"activityId":11,"activityBusinessNumber":"1001","activityName":"孤独求败 ","activityStartTime":"2017-04-28 10:10:10.0","activityEndTime":"2017-05-20 00:00:00.0","activityExplain":"孤独求败 ","activityShare":null,"activityStatus":1},{"activityId":15,"activityBusinessNumber":"1001","activityName":"五一总","activityStartTime":"2017-04-22 19:05:11.0","activityEndTime":"2017-05-20 00:00:00.0","activityExplain":"五一","activityShare":null,"activityStatus":1}]
         * productClassify : [{"classifyId":2,"classifyName":"视频食品","classifyHigherId":0,"classifyLevel":1,"classifyDisplay":1,"classifyBusinessNumber":"1001"},{"classifyId":3,"classifyName":"饮品","classifyHigherId":0,"classifyLevel":1,"classifyDisplay":1,"classifyBusinessNumber":"1001"},{"classifyId":10,"classifyName":"冰淇淋","classifyHigherId":0,"classifyLevel":1,"classifyDisplay":1,"classifyBusinessNumber":"1001"}]
         */

        private double score;
        private BusinessBean business;
        private double startMoney;
        private List<ProductBean> product;
        private List<ActivityBean> activity;
        private List<ProductClassifyBean> productClassify;

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

        public List<ProductBean> getProduct() {
            return product;
        }

        public void setProduct(List<ProductBean> product) {
            this.product = product;
        }

        public List<ActivityBean> getActivity() {
            return activity;
        }

        public void setActivity(List<ActivityBean> activity) {
            this.activity = activity;
        }

        public List<ProductClassifyBean> getProductClassify() {
            return productClassify;
        }

        public void setProductClassify(List<ProductClassifyBean> productClassify) {
            this.productClassify = productClassify;
        }

        public double getStartMoney() {
            return startMoney;
        }

        public void setStartMoney(double startMoney) {
            this.startMoney = startMoney;
        }

        public static class BusinessBean implements Serializable {
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
            private int sellOrderNum;

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

            public int getSellOrderNum() {
                return sellOrderNum;
            }

            public void setSellOrderNum(int sellOrderNum) {
                this.sellOrderNum = sellOrderNum;
            }

            @Override
            public String toString() {
                return "BusinessBean{" +
                        "businessId=" + businessId +
                        ", businessNumber='" + businessNumber + '\'' +
                        ", businessCompany='" + businessCompany + '\'' +
                        ", businessCompanyId=" + businessCompanyId +
                        ", businessProjectName='" + businessProjectName + '\'' +
                        ", businessProjectId=" + businessProjectId +
                        ", businessName='" + businessName + '\'' +
                        ", businessLogo='" + businessLogo + '\'' +
                        ", businessAddress='" + businessAddress + '\'' +
                        ", businessProperty=" + businessProperty +
                        ", businessPersonInCharge='" + businessPersonInCharge + '\'' +
                        ", businessPhone='" + businessPhone + '\'' +
                        ", businessProduceType=" + businessProduceType +
                        ", businessPlatformBonus=" + businessPlatformBonus +
                        ", businessCompanyBonus=" + businessCompanyBonus +
                        ", businessJoinTime='" + businessJoinTime + '\'' +
                        ", businessStatus=" + businessStatus +
                        ", businessDetails='" + businessDetails + '\'' +
                        ", businessServerIntroduce='" + businessServerIntroduce + '\'' +
                        ", sellOrderNum=" + sellOrderNum +
                        '}';
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

        public static class ProductClassifyBean {
            /**
             * classifyId : 2
             * classifyName : 视频食品
             * classifyHigherId : 0
             * classifyLevel : 1
             * classifyDisplay : 1
             * classifyBusinessNumber : 1001
             */

            private int classifyId;
            private String classifyName;
            private int classifyHigherId;
            private int classifyLevel;
            private int classifyDisplay;
            private String classifyBusinessNumber;

            private boolean isSelect;

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public int getClassifyId() {
                return classifyId;
            }

            public void setClassifyId(int classifyId) {
                this.classifyId = classifyId;
            }

            public String getClassifyName() {
                return classifyName;
            }

            public void setClassifyName(String classifyName) {
                this.classifyName = classifyName;
            }

            public int getClassifyHigherId() {
                return classifyHigherId;
            }

            public void setClassifyHigherId(int classifyHigherId) {
                this.classifyHigherId = classifyHigherId;
            }

            public int getClassifyLevel() {
                return classifyLevel;
            }

            public void setClassifyLevel(int classifyLevel) {
                this.classifyLevel = classifyLevel;
            }

            public int getClassifyDisplay() {
                return classifyDisplay;
            }

            public void setClassifyDisplay(int classifyDisplay) {
                this.classifyDisplay = classifyDisplay;
            }

            public String getClassifyBusinessNumber() {
                return classifyBusinessNumber;
            }

            public void setClassifyBusinessNumber(String classifyBusinessNumber) {
                this.classifyBusinessNumber = classifyBusinessNumber;
            }

            @Override
            public String toString() {
                return "ProductClassifyBean{" +
                        "classifyId=" + classifyId +
                        ", classifyName='" + classifyName + '\'' +
                        ", classifyHigherId=" + classifyHigherId +
                        ", classifyLevel=" + classifyLevel +
                        ", classifyDisplay=" + classifyDisplay +
                        ", classifyBusinessNumber='" + classifyBusinessNumber + '\'' +
                        ", isSelect=" + isSelect +
                        '}';
            }
        }

        public static class ProductBean {
            /**
             * productId : 96
             * productBusinessNumber : 1003
             * productBusinessName : 三盐两语
             * productName : 辣椒炒肉
             * productPrice : 12
             * productPreferentialPrice : 12
             * productImage : upload/img/1510126468656,upload/img/1510126468656,
             * upload/img/classify/1.JPEG,upload/img/classify/2.JPEG,upload/img/classify/2.JPEG,
             * upload/img/classify/4.JPEG,upload/img/classify/5.jpg
             * productDetails : 辣椒炒肉
             * productNum : 20
             * updateTime : 2017-12-13 11:00:26.0
             * update_status : 1
             * uploadTime : 2017-12-13 11:00:26.0
             * productSalesNumber : 1
             * productPraiseRate : 100
             * productClassifyId : 1
             * productClassName : 炒菜
             */

            private int productId;
            private String productBusinessNumber;
            private String productBusinessName;
            private String productName;
            private double productPrice;
            private double productPreferentialPrice;
            private String productImage;
            private String productDetails;
            private int productNum;
            private String updateTime;
            private int update_status;
            private String uploadTime;
            private int productSalesNumber;
            private int productPraiseRate;
            private int productClassifyId;
            private String productClassName;

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductBusinessNumber() {
                return productBusinessNumber;
            }

            public void setProductBusinessNumber(String productBusinessNumber) {
                this.productBusinessNumber = productBusinessNumber;
            }

            public String getProductBusinessName() {
                return productBusinessName;
            }

            public void setProductBusinessName(String productBusinessName) {
                this.productBusinessName = productBusinessName;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public double getProductPrice() {
                return productPrice;
            }

            public void setProductPrice(double productPrice) {
                this.productPrice = productPrice;
            }

            public double getProductPreferentialPrice() {
                return productPreferentialPrice;
            }

            public void setProductPreferentialPrice(double productPreferentialPrice) {
                this.productPreferentialPrice = productPreferentialPrice;
            }

            public String getProductImage() {
                return productImage;
            }

            public void setProductImage(String productImage) {
                this.productImage = productImage;
            }

            public String getProductDetails() {
                return productDetails;
            }

            public void setProductDetails(String productDetails) {
                this.productDetails = productDetails;
            }

            public int getProductNum() {
                return productNum;
            }

            public void setProductNum(int productNum) {
                this.productNum = productNum;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getUpdate_status() {
                return update_status;
            }

            public void setUpdate_status(int update_status) {
                this.update_status = update_status;
            }

            public String getUploadTime() {
                return uploadTime;
            }

            public void setUploadTime(String uploadTime) {
                this.uploadTime = uploadTime;
            }

            public int getProductSalesNumber() {
                return productSalesNumber;
            }

            public void setProductSalesNumber(int productSalesNumber) {
                this.productSalesNumber = productSalesNumber;
            }

            public int getProductPraiseRate() {
                return productPraiseRate;
            }

            public void setProductPraiseRate(int productPraiseRate) {
                this.productPraiseRate = productPraiseRate;
            }

            public int getProductClassifyId() {
                return productClassifyId;
            }

            public void setProductClassifyId(int productClassifyId) {
                this.productClassifyId = productClassifyId;
            }

            public String getProductClassName() {
                return productClassName;
            }

            public void setProductClassName(String productClassName) {
                this.productClassName = productClassName;
            }

            @Override
            public String toString() {
                return "ProductBean{" +
                        "productId=" + productId +
                        ", productBusinessNumber='" + productBusinessNumber + '\'' +
                        ", productBusinessName='" + productBusinessName + '\'' +
                        ", productName='" + productName + '\'' +
                        ", productPrice=" + productPrice +
                        ", productPreferentialPrice=" + productPreferentialPrice +
                        ", productImage='" + productImage + '\'' +
                        ", productDetails='" + productDetails + '\'' +
                        ", productNum=" + productNum +
                        ", updateTime='" + updateTime + '\'' +
                        ", update_status=" + update_status +
                        ", uploadTime='" + uploadTime + '\'' +
                        ", productSalesNumber=" + productSalesNumber +
                        ", productPraiseRate=" + productPraiseRate +
                        ", productClassifyId=" + productClassifyId +
                        ", productClassName='" + productClassName + '\'' +
                        '}';
            }
        }
    }
}
