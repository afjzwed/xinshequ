package com.yxld.yxchuangxin.entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * @ClassName: AppYezhuFangwu
 * @Description:  App业主房屋中间表
 * @author wwx
 * @date 2016-11-24 下午6:09:20
 */
public class AppYezhuFangwu extends BaseEntity implements Parcelable{

	private List<AppYezhuFangwu> rows;

	private int yezhuId; //业主id

	/** 房屋ID*/
	private int fwId;
	/** 房屋楼栋*/
	private String fwLoudong;
	/** 房屋单元*/
	private String fwDanyuan;
	/** 房号*/
	private String fwFanghao;
	/** 房屋户型*/
	private String fwHuxing;
	/** 房屋面积*/
	private String fwMainji;
	/** 楼盘ID*/
	private int fwLoupanId;
	/** 业主房屋关系类型：0产权人1家属2租客3其他4.历史产权人*/
	private int fwyzType;

	/** 房屋楼盘名称*/
	private String xiangmuLoupan; //楼盘
	/** 小区电话*/
	private String xiangmuTelphone;//电话号码

	private String yezhuName; //业主姓名

	//性别
	private int sex;

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public static Creator<AppYezhuFangwu> getCREATOR() {
		return CREATOR;
	}

	public AppYezhuFangwu() {
	}

	protected AppYezhuFangwu(Parcel in) {
		rows = in.createTypedArrayList(AppYezhuFangwu.CREATOR);
		yezhuId = in.readInt();
		fwId = in.readInt();
		fwLoudong = in.readString();
		fwDanyuan = in.readString();
		fwFanghao = in.readString();
		fwHuxing = in.readString();
		fwMainji = in.readString();
		fwLoupanId = in.readInt();
		fwyzType = in.readInt();
		xiangmuLoupan = in.readString();
		xiangmuTelphone = in.readString();
		yezhuName = in.readString();
	}

	public static final Creator<AppYezhuFangwu> CREATOR = new Creator<AppYezhuFangwu>() {
		@Override
		public AppYezhuFangwu createFromParcel(Parcel in) {
			return new AppYezhuFangwu(in);
		}

		@Override
		public AppYezhuFangwu[] newArray(int size) {
			return new AppYezhuFangwu[size];
		}
	};

	public List<AppYezhuFangwu> getRows() {
		return rows;
	}

	public void setRows(List<AppYezhuFangwu> rows) {
		this.rows = rows;
	}

	public int getYezhuId() {
		return yezhuId;
	}

	public void setYezhuId(int yezhuId) {
		this.yezhuId = yezhuId;
	}

	public int getFwId() {
		return fwId;
	}

	public void setFwId(int fwId) {
		this.fwId = fwId;
	}

	public String getFwLoudong() {
		return fwLoudong;
	}

	public void setFwLoudong(String fwLoudong) {
		this.fwLoudong = fwLoudong;
	}

	public String getFwDanyuan() {
		return fwDanyuan;
	}

	public void setFwDanyuan(String fwDanyuan) {
		this.fwDanyuan = fwDanyuan;
	}

	public String getFwFanghao() {
		return fwFanghao;
	}

	public void setFwFanghao(String fwFanghao) {
		this.fwFanghao = fwFanghao;
	}

	public String getFwHuxing() {
		return fwHuxing;
	}

	public void setFwHuxing(String fwHuxing) {
		this.fwHuxing = fwHuxing;
	}

	public String getFwMainji() {
		return fwMainji;
	}

	public void setFwMainji(String fwMainji) {
		this.fwMainji = fwMainji;
	}

	public int getFwLoupanId() {
		return fwLoupanId;
	}

	public void setFwLoupanId(int fwLoupanId) {
		this.fwLoupanId = fwLoupanId;
	}

	public int getFwyzType() {
		return fwyzType;
	}

	public void setFwyzType(int fwyzType) {
		this.fwyzType = fwyzType;
	}

	public String getXiangmuLoupan() {
		return xiangmuLoupan;
	}

	public void setXiangmuLoupan(String xiangmuLoupan) {
		this.xiangmuLoupan = xiangmuLoupan;
	}

	public String getXiangmuTelphone() {
		return xiangmuTelphone;
	}

	public void setXiangmuTelphone(String xiangmuTelphone) {
		this.xiangmuTelphone = xiangmuTelphone;
	}

	public String getYezhuName() {
		return yezhuName;
	}

	public void setYezhuName(String yezhuName) {
		this.yezhuName = yezhuName;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeTypedList(rows);
		dest.writeInt(yezhuId);
		dest.writeInt(fwId);
		dest.writeString(fwLoudong);
		dest.writeString(fwDanyuan);
		dest.writeString(fwFanghao);
		dest.writeString(fwHuxing);
		dest.writeString(fwMainji);
		dest.writeInt(fwLoupanId);
		dest.writeInt(fwyzType);
		dest.writeString(xiangmuLoupan);
		dest.writeString(xiangmuTelphone);
		dest.writeString(yezhuName);
	}

	@Override
	public String toString() {
		return "AppYezhuFangwu{" +
				"rows=" + rows +
				", yezhuId=" + yezhuId +
				", fwId=" + fwId +
				", fwLoudong='" + fwLoudong + '\'' +
				", fwDanyuan='" + fwDanyuan + '\'' +
				", fwFanghao='" + fwFanghao + '\'' +
				", fwHuxing='" + fwHuxing + '\'' +
				", fwMainji='" + fwMainji + '\'' +
				", fwLoupanId=" + fwLoupanId +
				", fwyzType=" + fwyzType +
				", xiangmuLoupan='" + xiangmuLoupan + '\'' +
				", xiangmuTelphone='" + xiangmuTelphone + '\'' +
				", yezhuName='" + yezhuName + '\'' +
				'}';
	}
}
