package com.yxld.yxchuangxin.entity;

/**
 * CxwyXiangmu entity. @author MyEclipse Persistence Tools
 */

public class CxwyXiangmu {

	// Fields
	// Fields
	private Integer xiangmuId; //id
	private String xiangmuLoupan; //楼盘
	private String xiangmuZimupingyin;
	private String xiangmuTelphone;//电话号码

	//经纬度
	private String xiangmuLatitude; //纬度
	private String xiangmuLongitude; //经度
	private Integer xiangmuIsUse;

	// Constructors

	/** default constructor */
	public CxwyXiangmu() {
	}

	public CxwyXiangmu(Integer xiangmuId, String xiangmuLoupan, String xiangmuZimupingyin, String xiangmuTelphone, String xiangmuLatitude, String xiangmuLongitude, Integer xiangmuIsUse) {
		this.xiangmuId = xiangmuId;
		this.xiangmuLoupan = xiangmuLoupan;
		this.xiangmuZimupingyin = xiangmuZimupingyin;
		this.xiangmuTelphone = xiangmuTelphone;
		this.xiangmuLatitude = xiangmuLatitude;
		this.xiangmuLongitude = xiangmuLongitude;
		this.xiangmuIsUse = xiangmuIsUse;
	}

// Property accessors


	public Integer getXiangmuId() {
		return xiangmuId;
	}

	public void setXiangmuId(Integer xiangmuId) {
		this.xiangmuId = xiangmuId;
	}

	public String getXiangmuLoupan() {
		return xiangmuLoupan;
	}

	public void setXiangmuLoupan(String xiangmuLoupan) {
		this.xiangmuLoupan = xiangmuLoupan;
	}

	public String getXiangmuZimupingyin() {
		return xiangmuZimupingyin;
	}

	public void setXiangmuZimupingyin(String xiangmuZimupingyin) {
		this.xiangmuZimupingyin = xiangmuZimupingyin;
	}

	public String getXiangmuTelphone() {
		return xiangmuTelphone;
	}

	public void setXiangmuTelphone(String xiangmuTelphone) {
		this.xiangmuTelphone = xiangmuTelphone;
	}

	public String getXiangmuLatitude() {
		return xiangmuLatitude;
	}

	public void setXiangmuLatitude(String xiangmuLatitude) {
		this.xiangmuLatitude = xiangmuLatitude;
	}

	public String getXiangmuLongitude() {
		return xiangmuLongitude;
	}

	public void setXiangmuLongitude(String xiangmuLongitude) {
		this.xiangmuLongitude = xiangmuLongitude;
	}

	public Integer getXiangmuIsUse() {
		return xiangmuIsUse;
	}

	public void setXiangmuIsUse(Integer xiangmuIsUse) {
		this.xiangmuIsUse = xiangmuIsUse;
	}

	@Override
	public String toString() {
		return "CxwyXiangmu{" +
				"xiangmuId=" + xiangmuId +
				", xiangmuLoupan='" + xiangmuLoupan + '\'' +
				", xiangmuZimupingyin='" + xiangmuZimupingyin + '\'' +
				", xiangmuTelphone='" + xiangmuTelphone + '\'' +
				", xiangmuLatitude='" + xiangmuLatitude + '\'' +
				", xiangmuLongitude='" + xiangmuLongitude + '\'' +
				", xiangmuIsUse=" + xiangmuIsUse +
				'}';
	}
}