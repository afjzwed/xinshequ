package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * CxwyJfWyRecord entity. @author Carrol
 */

public class CxwyJfWyRecord extends BaseEntity implements java.io.Serializable {


	private List<CxwyJfWyRecord> house;

	public List<CxwyJfWyRecord> getHouse() {
		return house;
	}

	public void setHouse(List<CxwyJfWyRecord> house) {
		this.house = house;
	}
	// Fields

	private Integer jfWyRecId;
	private Integer jfWyRecXmId;
	private String jfWyRecDong;
	private String jfWyRecDanyuan;
	private String jfWyRecFanghao;
	private String jfWyRecMianji;
	private String jfWyRecFwStatus;
	private String jfWyRecCreateTime;
	private String jfWyRecUseEndTime;
	private String jfWyRecTypeName;
	private Double jfWyRecPrice;
	private Double jfWyRecTotal;
	private String jfWyRecRemark;
	private Integer jfWyRecJiaofeiType;
	private String jfWyRecLiushui;
	private String jfWyRecJiaofeirenName;
	private String jfWyRecChanquanren;
	private Double jfWyRecLateFees;
	private Integer jfWyRecYzId;
	private Integer jfWyRecFwId;
	private String cString;
	private String uString;


	// Constructors

	/** default constructor */
	public CxwyJfWyRecord() {
	}

	public CxwyJfWyRecord(Integer jfWyRecId, Integer jfWyRecXmId, String jfWyRecDong, String jfWyRecDanyuan, String jfWyRecFanghao, String jfWyRecMianji, String jfWyRecFwStatus, String jfWyRecCreateTime, String jfWyRecUseEndTime, String jfWyRecTypeName, Double jfWyRecPrice, Double jfWyRecTotal, String jfWyRecRemark, Integer jfWyRecJiaofeiType, String jfWyRecLiushui, String jfWyRecJiaofeirenName, String jfWyRecChanquanren, Double jfWyRecLateFees, Integer jfWyRecYzId, Integer jfWyRecFwId, String cString, String uString) {
		this.jfWyRecId = jfWyRecId;
		this.jfWyRecXmId = jfWyRecXmId;
		this.jfWyRecDong = jfWyRecDong;
		this.jfWyRecDanyuan = jfWyRecDanyuan;
		this.jfWyRecFanghao = jfWyRecFanghao;
		this.jfWyRecMianji = jfWyRecMianji;
		this.jfWyRecFwStatus = jfWyRecFwStatus;
		this.jfWyRecCreateTime = jfWyRecCreateTime;
		this.jfWyRecUseEndTime = jfWyRecUseEndTime;
		this.jfWyRecTypeName = jfWyRecTypeName;
		this.jfWyRecPrice = jfWyRecPrice;
		this.jfWyRecTotal = jfWyRecTotal;
		this.jfWyRecRemark = jfWyRecRemark;
		this.jfWyRecJiaofeiType = jfWyRecJiaofeiType;
		this.jfWyRecLiushui = jfWyRecLiushui;
		this.jfWyRecJiaofeirenName = jfWyRecJiaofeirenName;
		this.jfWyRecChanquanren = jfWyRecChanquanren;
		this.jfWyRecLateFees = jfWyRecLateFees;
		this.jfWyRecYzId = jfWyRecYzId;
		this.jfWyRecFwId = jfWyRecFwId;
		this.cString = cString;
		this.uString = uString;
	}

// Property accessors

	public Integer getJfWyRecId() {
		return this.jfWyRecId;
	}

	public void setJfWyRecId(Integer jfWyRecId) {
		this.jfWyRecId = jfWyRecId;
	}

	public Integer getJfWyRecXmId() {
		return this.jfWyRecXmId;
	}

	public void setJfWyRecXmId(Integer jfWyRecXmId) {
		this.jfWyRecXmId = jfWyRecXmId;
	}

	public String getJfWyRecDong() {
		return this.jfWyRecDong;
	}

	public void setJfWyRecDong(String jfWyRecDong) {
		this.jfWyRecDong = jfWyRecDong;
	}

	public String getJfWyRecDanyuan() {
		return this.jfWyRecDanyuan;
	}

	public void setJfWyRecDanyuan(String jfWyRecDanyuan) {
		this.jfWyRecDanyuan = jfWyRecDanyuan;
	}

	public String getJfWyRecFanghao() {
		return this.jfWyRecFanghao;
	}

	public void setJfWyRecFanghao(String jfWyRecFanghao) {
		this.jfWyRecFanghao = jfWyRecFanghao;
	}

	public String getJfWyRecMianji() {
		return this.jfWyRecMianji;
	}

	public void setJfWyRecMianji(String jfWyRecMianji) {
		this.jfWyRecMianji = jfWyRecMianji;
	}

	public String getJfWyRecFwStatus() {
		return this.jfWyRecFwStatus;
	}

	public void setJfWyRecFwStatus(String jfWyRecFwStatus) {
		this.jfWyRecFwStatus = jfWyRecFwStatus;
	}

	public String getJfWyRecCreateTime() {
		return this.jfWyRecCreateTime;
	}

	public void setJfWyRecCreateTime(String jfWyRecCreateTime) {
		this.jfWyRecCreateTime = jfWyRecCreateTime;
	}

	public String getJfWyRecUseEndTime() {
		return this.jfWyRecUseEndTime;
	}

	public void setJfWyRecUseEndTime(String jfWyRecUseEndTime) {
		this.jfWyRecUseEndTime = jfWyRecUseEndTime;
	}

	public String getJfWyRecTypeName() {
		return this.jfWyRecTypeName;
	}

	public void setJfWyRecTypeName(String jfWyRecTypeName) {
		this.jfWyRecTypeName = jfWyRecTypeName;
	}

	public Double getJfWyRecPrice() {
		return this.jfWyRecPrice;
	}

	public void setJfWyRecPrice(Double jfWyRecPrice) {
		this.jfWyRecPrice = jfWyRecPrice;
	}

	public Double getJfWyRecTotal() {
		return this.jfWyRecTotal;
	}

	public void setJfWyRecTotal(Double jfWyRecTotal) {
		this.jfWyRecTotal = jfWyRecTotal;
	}

	public String getJfWyRecRemark() {
		return this.jfWyRecRemark;
	}

	public void setJfWyRecRemark(String jfWyRecRemark) {
		this.jfWyRecRemark = jfWyRecRemark;
	}

	public Integer getJfWyRecJiaofeiType() {
		return this.jfWyRecJiaofeiType;
	}

	public void setJfWyRecJiaofeiType(Integer jfWyRecJiaofeiType) {
		this.jfWyRecJiaofeiType = jfWyRecJiaofeiType;
	}

	public String getJfWyRecLiushui() {
		return this.jfWyRecLiushui;
	}

	public void setJfWyRecLiushui(String jfWyRecLiushui) {
		this.jfWyRecLiushui = jfWyRecLiushui;
	}

	public String getJfWyRecJiaofeirenName() {
		return this.jfWyRecJiaofeirenName;
	}

	public void setJfWyRecJiaofeirenName(String jfWyRecJiaofeirenName) {
		this.jfWyRecJiaofeirenName = jfWyRecJiaofeirenName;
	}

	public String getJfWyRecChanquanren() {
		return this.jfWyRecChanquanren;
	}

	public void setJfWyRecChanquanren(String jfWyRecChanquanren) {
		this.jfWyRecChanquanren = jfWyRecChanquanren;
	}

	public Double getJfWyRecLateFees() {
		return jfWyRecLateFees;
	}

	public void setJfWyRecLateFees(Double jfWyRecLateFees) {
		this.jfWyRecLateFees = jfWyRecLateFees;
	}

	public Integer getJfWyRecYzId() {
		return jfWyRecYzId;
	}

	public void setJfWyRecYzId(Integer jfWyRecYzId) {
		this.jfWyRecYzId = jfWyRecYzId;
	}

	public Integer getJfWyRecFwId() {
		return jfWyRecFwId;
	}

	public void setJfWyRecFwId(Integer jfWyRecFwId) {
		this.jfWyRecFwId = jfWyRecFwId;
	}
	public String getuString() {
		return uString;
	}

	public void setuString(String uString) {
		this.uString = uString;
	}

	public String getcString() {
		return cString;
	}

	public void setcString(String cString) {
		this.cString = cString;
	}
}