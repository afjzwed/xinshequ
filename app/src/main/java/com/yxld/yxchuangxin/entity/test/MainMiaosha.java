package com.yxld.yxchuangxin.entity.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：hu on 2017/6/2
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class MainMiaosha {
    private float price;
    private float salePrice;
    private String imgUrl;
    private Integer url;
    private String shangpinId;//商品id

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getUrl() {
        return url;
    }

    public void setUrl(Integer url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getShangpinId() {
        return shangpinId;
    }

    public void setShangpinId(String shangpinId) {
        this.shangpinId = shangpinId;
    }
}
