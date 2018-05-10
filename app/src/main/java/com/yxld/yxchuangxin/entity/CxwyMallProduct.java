package com.yxld.yxchuangxin.entity;

/**
 * CxwyMallProduct entity. @author MyEclipse Persistence Tools
 */


import android.os.Parcel;
import android.os.Parcelable;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.io.Serializable;

/**
 * @author wwx
 * @ClassName: CxwyMallProduct
 * @Description: 二级分类产品信息
 * @date 2016年3月14日 下午2:47:38
 */
@SuppressWarnings("serial")
public class CxwyMallProduct extends BaseEntity implements Parcelable {
    private CxwyMallProduct product;

    // Fields
    private Integer shangpinId;//商品id
    private String shangpinShangpName;//商品名/标题名
    private String shangpinGuige;//规格
    private String shangpinRmb;//单价
    private Integer shangpinHave;//是否缺货0是缺货，1是有货
    private Integer shangpinNum;//商品库存
    private String shangpinBody;//详细介绍
    private Integer shangpinZhuangtai;//状态，是否上架0为是，1为否
    private String shangpinImgSrc1;//图片用；号拼接
    private String shangpinClassicOne;//一级分类
    private String shangpinClassicTwo;//二级分类
    private Integer shangpinClassicShow;//商品首页活动商品区展示，0为是，1为否

    private String shangpinProject;//所属项目
    private String shangpinNight;//是否夜间配送商品 0 否 1是
    private String shangpinUploadTime; //商品上传时间
    private String shangpinBianhao; //商品编号
    private String shangpinJinhuojia; //进货价
    private String shangpinBeiyong5; //0 不是大件商品 1大件商品
    //生产日期
    private String shangpinShenchanriqi;
    //有效期
    private String shangpinYouxiaoqi;
    //过期日期
    private String shangpinGuoqiriqi;
    private String xnName;//项目名称
    //是否销量商品
    private Integer shangpinXiaoliang;
    //是否人气商品
    private Integer shangpinRenqi;

    /**
     * default constructor
     */
    public CxwyMallProduct() {
    }

    public CxwyMallProduct(Integer shangpinId, String shangpinShangpName, String shangpinRmb) {
        super();
        this.shangpinId = shangpinId;
        this.shangpinShangpName = shangpinShangpName;
        this.shangpinRmb = shangpinRmb;
    }

    public CxwyMallProduct(String shangpinShangpName, String shangpinGuige, String shangpinRmb, Integer shangpinHave, Integer shangpinNum, String shangpinBody, Integer shangpinZhuangtai, String shangpinImgSrc1, String shangpinClassicOne, String shangpinClassicTwo, Integer shangpinClassicShow, String shangpinProject, String shangpinNight, String shangpinUploadTime, String shangpinBianhao, String shangpinJinhuojia, String shangpinBeiyong5) {
        this.shangpinShangpName = shangpinShangpName;
        this.shangpinGuige = shangpinGuige;
        this.shangpinRmb = shangpinRmb;
        this.shangpinHave = shangpinHave;
        this.shangpinNum = shangpinNum;
        this.shangpinBody = shangpinBody;
        this.shangpinZhuangtai = shangpinZhuangtai;
        this.shangpinImgSrc1 = shangpinImgSrc1;
        this.shangpinClassicOne = shangpinClassicOne;
        this.shangpinClassicTwo = shangpinClassicTwo;
        this.shangpinClassicShow = shangpinClassicShow;
        this.shangpinProject = shangpinProject;
        this.shangpinNight = shangpinNight;
        this.shangpinUploadTime = shangpinUploadTime;
        this.shangpinBianhao = shangpinBianhao;
        this.shangpinJinhuojia = shangpinJinhuojia;
        this.shangpinBeiyong5 = shangpinBeiyong5;
    }

    protected CxwyMallProduct(Parcel in) {
        product = in.readParcelable(CxwyMallProduct.class.getClassLoader());
        shangpinShangpName = in.readString();
        shangpinGuige = in.readString();
        shangpinRmb = in.readString();
        shangpinBody = in.readString();
        shangpinImgSrc1 = in.readString();
        shangpinClassicOne = in.readString();
        shangpinClassicTwo = in.readString();
        shangpinProject = in.readString();
        shangpinNight = in.readString();
        shangpinUploadTime = in.readString();
        shangpinBianhao = in.readString();
        shangpinJinhuojia = in.readString();
        shangpinBeiyong5 = in.readString();
        shangpinShenchanriqi = in.readString();
        shangpinYouxiaoqi = in.readString();
        shangpinGuoqiriqi = in.readString();
        shangpinId = in.readInt();
        xnName = in.readString();
        shangpinHave = in.readInt();
        shangpinNum = in.readInt();
        shangpinZhuangtai = in.readInt();
        shangpinClassicShow = in.readInt();
        shangpinRenqi = in.readInt();
    }

    public static final Creator<CxwyMallProduct> CREATOR = new Creator<CxwyMallProduct>() {
        @Override
        public CxwyMallProduct createFromParcel(Parcel in) {
            return new CxwyMallProduct(in);
        }

        @Override
        public CxwyMallProduct[] newArray(int size) {
            return new CxwyMallProduct[size];
        }
    };

    public Integer getShangpinId() {
        return shangpinId;
    }

    public void setShangpinId(Integer shangpinId) {
        this.shangpinId = shangpinId;
    }

    public String getShangpinShangpName() {
        return shangpinShangpName;
    }

    public void setShangpinShangpName(String shangpinShangpName) {
        this.shangpinShangpName = shangpinShangpName;
    }

    public String getShangpinGuige() {
        return shangpinGuige;
    }

    public void setShangpinGuige(String shangpinGuige) {
        this.shangpinGuige = shangpinGuige;
    }

    public String getShangpinRmb() {
        return shangpinRmb;
    }

    public void setShangpinRmb(String shangpinRmb) {
        this.shangpinRmb = shangpinRmb;
    }

    public Integer getShangpinHave() {
        return shangpinHave;
    }

    public void setShangpinHave(Integer shangpinHave) {
        this.shangpinHave = shangpinHave;
    }

    public Integer getShangpinNum() {
        return shangpinNum;
    }

    public void setShangpinNum(Integer shangpinNum) {
        this.shangpinNum = shangpinNum;
    }

    public String getShangpinBody() {
        return shangpinBody;
    }

    public void setShangpinBody(String shangpinBody) {
        this.shangpinBody = shangpinBody;
    }

    public Integer getShangpinZhuangtai() {
        return shangpinZhuangtai;
    }

    public void setShangpinZhuangtai(Integer shangpinZhuangtai) {
        this.shangpinZhuangtai = shangpinZhuangtai;
    }

    public String getShangpinImgSrc1() {
        return shangpinImgSrc1;
    }

    public void setShangpinImgSrc1(String shangpinImgSrc1) {
        this.shangpinImgSrc1 = shangpinImgSrc1;
    }

    public String getShangpinClassicOne() {
        return shangpinClassicOne;
    }

    public void setShangpinClassicOne(String shangpinClassicOne) {
        this.shangpinClassicOne = shangpinClassicOne;
    }

    public String getShangpinClassicTwo() {
        return shangpinClassicTwo;
    }

    public void setShangpinClassicTwo(String shangpinClassicTwo) {
        this.shangpinClassicTwo = shangpinClassicTwo;
    }

    public Integer getShangpinClassicShow() {
        return shangpinClassicShow;
    }

    public void setShangpinClassicShow(Integer shangpinClassicShow) {
        this.shangpinClassicShow = shangpinClassicShow;
    }

    public String getShangpinProject() {
        return shangpinProject;
    }

    public void setShangpinProject(String shangpinProject) {
        this.shangpinProject = shangpinProject;
    }

    public String getShangpinNight() {
        return shangpinNight;
    }

    public void setShangpinNight(String shangpinNight) {
        this.shangpinNight = shangpinNight;
    }

    public String getShangpinUploadTime() {
        return shangpinUploadTime;
    }

    public void setShangpinUploadTime(String shangpinUploadTime) {
        this.shangpinUploadTime = shangpinUploadTime;
    }

    public String getShangpinBianhao() {
        return shangpinBianhao;
    }

    public void setShangpinBianhao(String shangpinBianhao) {
        this.shangpinBianhao = shangpinBianhao;
    }

    public String getShangpinJinhuojia() {
        return shangpinJinhuojia;
    }

    public void setShangpinJinhuojia(String shangpinJinhuojia) {
        this.shangpinJinhuojia = shangpinJinhuojia;
    }

    public String getShangpinBeiyong5() {
        return shangpinBeiyong5;
    }

    public void setShangpinBeiyong5(String shangpinBeiyong5) {
        this.shangpinBeiyong5 = shangpinBeiyong5;
    }

    public CxwyMallProduct getProduct() {
        return product;
    }

    public void setProduct(CxwyMallProduct product) {
        this.product = product;
    }

    public String getShangpinShenchanriqi() {
        return shangpinShenchanriqi;
    }

    public void setShangpinShenchanriqi(String shangpinShenchanriqi) {
        this.shangpinShenchanriqi = shangpinShenchanriqi;
    }

    public String getShangpinYouxiaoqi() {
        return shangpinYouxiaoqi;
    }

    public void setShangpinYouxiaoqi(String shangpinYouxiaoqi) {
        this.shangpinYouxiaoqi = shangpinYouxiaoqi;
    }

    public String getShangpinGuoqiriqi() {
        return shangpinGuoqiriqi;
    }

    public void setShangpinGuoqiriqi(String shangpinGuoqiriqi) {
        this.shangpinGuoqiriqi = shangpinGuoqiriqi;
    }

    public String getXnName() {
        return xnName;
    }

    public void setXnName(String xnName) {
        this.xnName = xnName;
    }

    public Integer getShangpinXiaoliang() {
        return shangpinXiaoliang;
    }

    public void setShangpinXiaoliang(Integer shangpinXiaoliang) {
        this.shangpinXiaoliang = shangpinXiaoliang;
    }

    public Integer getShangpinRenqi() {
        return shangpinRenqi;
    }

    public void setShangpinRenqi(Integer shangpinRenqi) {
        this.shangpinRenqi = shangpinRenqi;
    }

    @Override
    public String toString() {
        return "CxwyMallProduct{" +
                "shangpinId=" + shangpinId +
                ", shangpinShangpName='" + shangpinShangpName + '\'' +
                ", shangpinGuige='" + shangpinGuige + '\'' +
                ", shangpinRmb='" + shangpinRmb + '\'' +
                ", shangpinHave=" + shangpinHave +
                ", shangpinNum=" + shangpinNum +
                ", shangpinBody='" + shangpinBody + '\'' +
                ", shangpinZhuangtai=" + shangpinZhuangtai +
                ", shangpinImgSrc1='" + shangpinImgSrc1 + '\'' +
                ", shangpinClassicOne='" + shangpinClassicOne + '\'' +
                ", shangpinClassicTwo='" + shangpinClassicTwo + '\'' +
                ", shangpinClassicShow=" + shangpinClassicShow +
                ", shangpinProject='" + shangpinProject + '\'' +
                ", shangpinNight='" + shangpinNight + '\'' +
                ", shangpinUploadTime='" + shangpinUploadTime + '\'' +
                ", shangpinBianhao='" + shangpinBianhao + '\'' +
                ", shangpinJinhuojia='" + shangpinJinhuojia + '\'' +
                ", shangpinBeiyong5='" + shangpinBeiyong5 + '\'' +
                ", shangpinShenchanriqi='" + shangpinShenchanriqi + '\'' +
                ", shangpinYouxiaoqi='" + shangpinYouxiaoqi + '\'' +
                ", shangpinGuoqiriqi='" + shangpinGuoqiriqi + '\'' +
                ", xnName='" + xnName + '\'' +
                ", shangpinXiaoliang=" + shangpinXiaoliang +
                ", shangpinRenqi=" + shangpinRenqi +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(product, flags);
        dest.writeString(shangpinShangpName);
        dest.writeString(shangpinGuige);
        dest.writeString(shangpinRmb);
        dest.writeString(shangpinBody);
        dest.writeString(shangpinImgSrc1);
        dest.writeString(shangpinClassicOne);
        dest.writeString(shangpinClassicTwo);
        dest.writeString(shangpinProject);
        dest.writeString(shangpinNight);
        dest.writeString(shangpinUploadTime);
        dest.writeString(shangpinBianhao);
        dest.writeString(shangpinJinhuojia);
        dest.writeString(shangpinBeiyong5);
        dest.writeString(shangpinShenchanriqi);
        dest.writeString(shangpinYouxiaoqi);
        dest.writeString(shangpinGuoqiriqi);
        dest.writeString(xnName);
        dest.writeInt(shangpinId);
        dest.writeInt(shangpinHave);
        dest.writeInt(shangpinNum);
        dest.writeInt(shangpinZhuangtai);
        dest.writeInt(shangpinClassicShow);
        dest.writeInt(shangpinRenqi);
    }
}