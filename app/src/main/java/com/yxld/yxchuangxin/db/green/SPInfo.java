package com.yxld.yxchuangxin.db.green;

import com.nostra13.universalimageloader.utils.L;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author xlei
 * @Date 2017/12/19.
 */
@Entity
public class SPInfo {
    @Id
    private Long id;
    private long productId;
    private String businessNumber;//根据这个字段和商家对应
    private String productImage;
    private int count;  //添加的数量
    private double productPrice;
    private double productPreferentialPrice;
    private String productName;
    private int productNum;//总库存


    @Generated(hash = 1929566433)
    public SPInfo(Long id, long productId, String businessNumber,
            String productImage, int count, double productPrice,
            double productPreferentialPrice, String productName, int productNum) {
        this.id = id;
        this.productId = productId;
        this.businessNumber = businessNumber;
        this.productImage = productImage;
        this.count = count;
        this.productPrice = productPrice;
        this.productPreferentialPrice = productPreferentialPrice;
        this.productName = productName;
        this.productNum = productNum;
    }


    @Generated(hash = 493153353)
    public SPInfo() {
    }


    @Override
    public String toString() {
        return "SPInfo{" +
                "id=" + id +
                ", productId=" + productId +
                ", businessNumber='" + businessNumber + '\'' +
                ", productImage='" + productImage + '\'' +
                ", count=" + count +
                ", productPrice=" + productPrice +
                ", productPreferentialPrice=" + productPreferentialPrice +
                ", productName='" + productName + '\'' +
                ", productNum=" + productNum +
                '}';
    }

    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public long getProductId() {
        return this.productId;
    }


    public void setProductId(long productId) {
        this.productId = productId;
    }


    public String getBusinessNumber() {
        return this.businessNumber;
    }


    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }


    public String getProductImage() {
        return this.productImage;
    }


    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }


    public int getCount() {
        return this.count;
    }


    public void setCount(int count) {
        this.count = count;
    }


    public double getProductPrice() {
        return this.productPrice;
    }


    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }


    public String getProductName() {
        return this.productName;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }


    public int getProductNum() {
        return this.productNum;
    }


    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }


    public double getProductPreferentialPrice() {
        return this.productPreferentialPrice;
    }


    public void setProductPreferentialPrice(double productPreferentialPrice) {
        this.productPreferentialPrice = productPreferentialPrice;
    }

}
