package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

/**
 * Created by Administrator on 2016/11/10.
 */

public class ProductInfo extends BaseEntity {

    private CxwyMallProduct product;

    public CxwyMallProduct getProduct() {
        return product;
    }

    public void setProduct(CxwyMallProduct product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "product=" + product +
                '}';
    }
}
