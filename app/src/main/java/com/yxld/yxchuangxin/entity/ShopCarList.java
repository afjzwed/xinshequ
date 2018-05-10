package com.yxld.yxchuangxin.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * @author xlei
 * @Date 2017/12/29.
 */

public class ShopCarList extends BaseEntity {
    private int total;
    private List<ShopCarBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ShopCarBean> getData() {
        return data;
    }

    public void setData(List<ShopCarBean> data) {
        this.data = data;
    }

    public static class ShopCarBean implements Parcelable {

        /**
         * cartNum : 1
         * productPreferentialPrice : 40
         * productDetails :
         * productBusinessNumber : 1001
         * productImage : upload/img/15101
         * productBusinessName : 快快洗
         * productPrice : 40
         * productName : 羽绒服中长款
         * productId : 91
         */

        private int cartNum;
        private double productPreferentialPrice;
        private String productDetails;
        private String productBusinessNumber;
        private String productImage;
        private String productBusinessName;
        private double productPrice;
        private String productName;
        private int productId;

        public ShopCarBean() {
        }

        protected ShopCarBean(Parcel in) {
            cartNum = in.readInt();
            productPreferentialPrice = in.readDouble();
            productDetails = in.readString();
            productBusinessNumber = in.readString();
            productImage = in.readString();
            productBusinessName = in.readString();
            productPrice = in.readDouble();
            productName = in.readString();
            productId = in.readInt();
        }

        public static final Creator<ShopCarBean> CREATOR = new Creator<ShopCarBean>() {
            @Override
            public ShopCarBean createFromParcel(Parcel in) {
                return new ShopCarBean(in);
            }

            @Override
            public ShopCarBean[] newArray(int size) {
                return new ShopCarBean[size];
            }
        };

        public int getCartNum() {
            return cartNum;
        }

        public void setCartNum(int cartNum) {
            this.cartNum = cartNum;
        }

        public double getProductPreferentialPrice() {
            return productPreferentialPrice;
        }

        public void setProductPreferentialPrice(double productPreferentialPrice) {
            this.productPreferentialPrice = productPreferentialPrice;
        }

        public String getProductDetails() {
            return productDetails;
        }

        public void setProductDetails(String productDetails) {
            this.productDetails = productDetails;
        }

        public String getProductBusinessNumber() {
            return productBusinessNumber;
        }

        public void setProductBusinessNumber(String productBusinessNumber) {
            this.productBusinessNumber = productBusinessNumber;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public String getProductBusinessName() {
            return productBusinessName;
        }

        public void setProductBusinessName(String productBusinessName) {
            this.productBusinessName = productBusinessName;
        }

        public double getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(double productPrice) {
            this.productPrice = productPrice;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(cartNum);
            dest.writeDouble(productPreferentialPrice);
            dest.writeString(productDetails);
            dest.writeString(productBusinessNumber);
            dest.writeString(productImage);
            dest.writeString(productBusinessName);
            dest.writeDouble(productPrice);
            dest.writeString(productName);
            dest.writeInt(productId);
        }
    }
}
