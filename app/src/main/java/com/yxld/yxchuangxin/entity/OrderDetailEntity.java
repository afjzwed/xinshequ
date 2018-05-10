package com.yxld.yxchuangxin.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/28
 * @descprition:
 */

public class OrderDetailEntity extends BaseEntity implements Parcelable{
    public CxwyMallOrder order;
    public List<CxwyMallSale> saleList;
    public String phone;

    public OrderDetailEntity(){

    }

    protected OrderDetailEntity(Parcel in) {
        order = in.readParcelable(CxwyMallOrder.class.getClassLoader());
        saleList = in.createTypedArrayList(CxwyMallSale.CREATOR);
        phone = in.readString();
    }

    public static final Creator<OrderDetailEntity> CREATOR = new Creator<OrderDetailEntity>() {
        @Override
        public OrderDetailEntity createFromParcel(Parcel in) {
            return new OrderDetailEntity(in);
        }

        @Override
        public OrderDetailEntity[] newArray(int size) {
            return new OrderDetailEntity[size];
        }
    };

    public CxwyMallOrder getOrder() {
        return order;
    }

    public void setOrder(CxwyMallOrder order) {
        this.order = order;
    }

    public List<CxwyMallSale> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<CxwyMallSale> saleList) {
        this.saleList = saleList;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(order, flags);
        dest.writeTypedList(saleList);
        dest.writeString(phone);
    }
}
