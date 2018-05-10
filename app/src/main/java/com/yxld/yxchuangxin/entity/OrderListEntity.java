package com.yxld.yxchuangxin.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/21
 * @descprition:
 */

public class OrderListEntity implements Serializable {
    public List<OrderListEntity> datas;
    public int statusCode;
    public String msg;

    public String orderMsg;
    public int orderStatusStatus;
    public String orderStatusOrderNumber;
    public String orderPlaceTime;
    public List<OrderProductEntity> orderProducts;


    public static class OrderProductEntity{
        public String productImgUrl;
        public String productName;
        public int productNum;
        public String productSpec;
        public double productPrice;

    }
}
