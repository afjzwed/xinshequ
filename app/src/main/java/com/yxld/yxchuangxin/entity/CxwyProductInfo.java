package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hu on 2017/5/3.
 */

public class CxwyProductInfo extends BaseEntity {

    /**
     * data : [{"productBusinessName":"","productBusinessNumber":"1001","productClassifyId":3,"productDetails":"12313","productId":32,"productImage":",","productName":"123","productNum":313,"productPraiseRate":0,"productPreferentialPrice":1322,"productPrice":123,"productSalesNumber":0,"updateTime":"2017-04-18 09:26:59.0","update_status":1},{"productBusinessNumber":"1001","productClassifyId":3,"productId":41,"productImage":"","productName":"苹果","productNum":23,"productPraiseRate":0,"productPreferentialPrice":123,"productPrice":231,"productSalesNumber":0,"updateTime":"2017-04-20 23:59:59.0","update_status":1},{"productBusinessNumber":"1001","productClassifyId":3,"productId":43,"productImage":"","productName":"qwe","productNum":98,"productPraiseRate":0,"productPreferentialPrice":89,"productPrice":89,"productSalesNumber":0,"updateTime":"2017-04-20 23:59:59.0","update_status":1}]
     * msg : 查询成功成功
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

    public static class DataBean implements Serializable {
        /**
         * productBusinessName :
         * productBusinessNumber : 1001
         * productClassifyId : 3
         * productDetails : 12313
         * productId : 32
         * productImage : ,
         * productName : 123
         * productNum : 313
         * productPraiseRate : 0
         * productPreferentialPrice : 1322.0
         * productPrice : 123.0
         * productSalesNumber : 0
         * updateTime : 2017-04-18 09:26:59.0
         * update_status : 1
         */

        private String productBusinessName;
        private String productBusinessNumber;
        private int productClassifyId;
        private String productDetails;
        private int productId;
        private String productImage;
        private String productName;
        private int productNum;
        private int productPraiseRate;
        private double productPreferentialPrice;
        private double productPrice;
        private int productSalesNumber;
        private String updateTime;
        private int update_status;
        private boolean isChecked;

        public String getProductBusinessName() {
            return productBusinessName;
        }

        public void setProductBusinessName(String productBusinessName) {
            this.productBusinessName = productBusinessName;
        }

        public String getProductBusinessNumber() {
            return productBusinessNumber;
        }

        public void setProductBusinessNumber(String productBusinessNumber) {
            this.productBusinessNumber = productBusinessNumber;
        }

        public int getProductClassifyId() {
            return productClassifyId;
        }

        public void setProductClassifyId(int productClassifyId) {
            this.productClassifyId = productClassifyId;
        }

        public String getProductDetails() {
            return productDetails;
        }

        public void setProductDetails(String productDetails) {
            this.productDetails = productDetails;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getProductNum() {
            return productNum;
        }

        public void setProductNum(int productNum) {
            this.productNum = productNum;
        }

        public int getProductPraiseRate() {
            return productPraiseRate;
        }

        public void setProductPraiseRate(int productPraiseRate) {
            this.productPraiseRate = productPraiseRate;
        }

        public double getProductPreferentialPrice() {
            return productPreferentialPrice;
        }

        public void setProductPreferentialPrice(double productPreferentialPrice) {
            this.productPreferentialPrice = productPreferentialPrice;
        }

        public double getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(double productPrice) {
            this.productPrice = productPrice;
        }

        public int getProductSalesNumber() {
            return productSalesNumber;
        }

        public void setProductSalesNumber(int productSalesNumber) {
            this.productSalesNumber = productSalesNumber;
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

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "productBusinessName='" + productBusinessName + '\'' +
                    ", productBusinessNumber='" + productBusinessNumber + '\'' +
                    ", productClassifyId=" + productClassifyId +
                    ", productDetails='" + productDetails + '\'' +
                    ", productId=" + productId +
                    ", productImage='" + productImage + '\'' +
                    ", productName='" + productName + '\'' +
                    ", productNum=" + productNum +
                    ", productPraiseRate=" + productPraiseRate +
                    ", productPreferentialPrice=" + productPreferentialPrice +
                    ", productPrice=" + productPrice +
                    ", productSalesNumber=" + productSalesNumber +
                    ", updateTime='" + updateTime + '\'' +
                    ", update_status=" + update_status +
                    ", isChecked=" + isChecked +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CxwyProductInfo{" +
                "success='" + success + '\'' +
                ", total=" + total +
                ", data=" + data +
                '}';
    }
}
