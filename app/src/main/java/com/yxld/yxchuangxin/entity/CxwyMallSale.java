package com.yxld.yxchuangxin.entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * CxwyMallSale entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CxwyMallSale extends BaseEntity implements Parcelable {

	private List<CxwyMallSale> saleList;
	private CxwyMallOrder order;

	private String phone;
	
	// Fields
	private int saleId;//id
	private String saleTime;
	private int saleShangpNum;//商品id
	private int saleDingdanId;//订单id
	private String saleShangpName;//商品名称
	private String saleGoodsBianhao;//商品编号
	private int saleNum;//数量
	private String saleGuige;//规格
	private Float saleTotalRmb;//总金额
	private Float saleOneRmb;//单价
	private String saleImgSrc;//图片
	private String saleUseTime;

	private String saleProject; //下单小区
	private String saleCardId;//暂时存放下单时购物车id
	private String saleOrderState; //订单状态
	private String saleOrderBianhao; //订单编号

	private String saleJinhuojia; //进货价

	private String comment;
	private double ratingNum;

	// Constructors

	/** default constructor */
	public CxwyMallSale() {
	}


	protected CxwyMallSale(Parcel in) {
		saleList = in.createTypedArrayList(CxwyMallSale.CREATOR);
		order = in.readParcelable(CxwyMallOrder.class.getClassLoader());
		phone = in.readString();
		saleId = in.readInt();
		saleTime = in.readString();
		saleShangpNum = in.readInt();
		saleDingdanId = in.readInt();
		saleShangpName = in.readString();
		saleGoodsBianhao = in.readString();
		saleNum = in.readInt();
		saleGuige = in.readString();
		saleImgSrc = in.readString();
		saleUseTime = in.readString();
		saleProject = in.readString();
		saleCardId = in.readString();
		saleOrderState = in.readString();
		saleOrderBianhao = in.readString();
		saleJinhuojia = in.readString();
		comment = in.readString();
		ratingNum = in.readDouble();
	}

	public static final Creator<CxwyMallSale> CREATOR = new Creator<CxwyMallSale>() {
		@Override
		public CxwyMallSale createFromParcel(Parcel in) {
			return new CxwyMallSale(in);
		}

		@Override
		public CxwyMallSale[] newArray(int size) {
			return new CxwyMallSale[size];
		}
	};

	public List<CxwyMallSale> getSaleList() {
		return saleList;
	}

	public void setSaleList(List<CxwyMallSale> saleList) {
		this.saleList = saleList;
	}

	public CxwyMallOrder getOrder() {
		return order;
	}

	public void setOrder(CxwyMallOrder order) {
		this.order = order;
	}

	public Integer getSaleId() {
		return saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	public String getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
	}

	public Integer getSaleShangpNum() {
		return saleShangpNum;
	}

	public void setSaleShangpNum(Integer saleShangpNum) {
		this.saleShangpNum = saleShangpNum;
	}

	public Integer getSaleDingdanId() {
		return saleDingdanId;
	}

	public void setSaleDingdanId(Integer saleDingdanId) {
		this.saleDingdanId = saleDingdanId;
	}

	public String getSaleShangpName() {
		return saleShangpName;
	}

	public void setSaleShangpName(String saleShangpName) {
		this.saleShangpName = saleShangpName;
	}

	public Integer getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public String getSaleGuige() {
		return saleGuige;
	}

	public void setSaleGuige(String saleGuige) {
		this.saleGuige = saleGuige;
	}

	public Float getSaleTotalRmb() {
		return saleTotalRmb;
	}

	public void setSaleTotalRmb(Float saleTotalRmb) {
		this.saleTotalRmb = saleTotalRmb;
	}

	public Float getSaleOneRmb() {
		return saleOneRmb;
	}

	public void setSaleOneRmb(Float saleOneRmb) {
		this.saleOneRmb = saleOneRmb;
	}

	public String getSaleImgSrc() {
		return saleImgSrc;
	}

	public void setSaleImgSrc(String saleImgSrc) {
		this.saleImgSrc = saleImgSrc;
	}

	public String getSaleUseTime() {
		return saleUseTime;
	}

	public void setSaleUseTime(String saleUseTime) {
		this.saleUseTime = saleUseTime;
	}

	public String getSaleProject() {
		return saleProject;
	}

	public void setSaleProject(String saleProject) {
		this.saleProject = saleProject;
	}

	public String getSaleCardId() {
		return saleCardId;
	}

	public void setSaleCardId(String saleCardId) {
		this.saleCardId = saleCardId;
	}

	public String getSaleOrderState() {
		return saleOrderState;
	}

	public void setSaleOrderState(String saleOrderState) {
		this.saleOrderState = saleOrderState;
	}

	public String getSaleOrderBianhao() {
		return saleOrderBianhao;
	}

	public void setSaleOrderBianhao(String saleOrderBianhao) {
		this.saleOrderBianhao = saleOrderBianhao;
	}

	public String getSaleJinhuojia() {
		return saleJinhuojia;
	}

	public void setSaleJinhuojia(String saleJinhuojia) {
		this.saleJinhuojia = saleJinhuojia;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public CxwyMallSale(String saleTime, Integer saleShangpNum, Integer saleDingdanId, String saleShangpName, Integer saleNum, String saleGuige, Float saleTotalRmb, Float saleOneRmb, String saleImgSrc, String saleUseTime, String saleProject, String saleCardId, String saleOrderState, String saleOrderBianhao, String saleJinhuojia) {
		this.saleTime = saleTime;
		this.saleShangpNum = saleShangpNum;
		this.saleDingdanId = saleDingdanId;
		this.saleShangpName = saleShangpName;
		this.saleNum = saleNum;
		this.saleGuige = saleGuige;
		this.saleTotalRmb = saleTotalRmb;
		this.saleOneRmb = saleOneRmb;
		this.saleImgSrc = saleImgSrc;
		this.saleUseTime = saleUseTime;
		this.saleProject = saleProject;
		this.saleCardId = saleCardId;
		this.saleOrderState = saleOrderState;
		this.saleOrderBianhao = saleOrderBianhao;
		this.saleJinhuojia = saleJinhuojia;
	}

	@Override
	public String toString() {
		return "CxwyMallSale{" +
				"saleList=" + saleList +
				", order=" + order +
				", saleId=" + saleId +
				", saleTime='" + saleTime + '\'' +
				", saleShangpNum=" + saleShangpNum +
				", saleDingdanId=" + saleDingdanId +
				", saleShangpName='" + saleShangpName + '\'' +
				", saleGoodsBianhao='" + saleGoodsBianhao + '\'' +
				", saleNum=" + saleNum +
				", saleGuige='" + saleGuige + '\'' +
				", saleTotalRmb=" + saleTotalRmb +
				", saleOneRmb=" + saleOneRmb +
				", saleImgSrc='" + saleImgSrc + '\'' +
				", saleUseTime='" + saleUseTime + '\'' +
				", saleProject='" + saleProject + '\'' +
				", saleCardId='" + saleCardId + '\'' +
				", saleOrderState='" + saleOrderState + '\'' +
				", saleOrderBianhao='" + saleOrderBianhao + '\'' +
				", saleJinhuojia='" + saleJinhuojia + '\'' +
				'}';
	}


	public String getSaleGoodsBianhao() {
		return saleGoodsBianhao;
	}

	public void setSaleGoodsBianhao(String saleGoodsBianhao) {
		this.saleGoodsBianhao = saleGoodsBianhao;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getRatingNum() {
		return ratingNum;
	}

	public void setRatingNum(double ratingNum) {
		this.ratingNum = ratingNum;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeTypedList(saleList);
		dest.writeParcelable(order, flags);
		dest.writeString(phone);
		dest.writeInt(saleId);
		dest.writeString(saleTime);
		dest.writeInt(saleShangpNum);
		dest.writeInt(saleDingdanId);
		dest.writeString(saleShangpName);
		dest.writeString(saleGoodsBianhao);
		dest.writeInt(saleNum);
		dest.writeString(saleGuige);
		dest.writeString(saleImgSrc);
		dest.writeString(saleUseTime);
		dest.writeString(saleProject);
		dest.writeString(saleCardId);
		dest.writeString(saleOrderState);
		dest.writeString(saleOrderBianhao);
		dest.writeString(saleJinhuojia);
		dest.writeString(comment);
		dest.writeDouble(ratingNum);
	}
}