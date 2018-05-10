package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Carrol：App返回房屋信息
 * 2016年12月15日 11:55:27
 */
public class
WyFwApp extends BaseEntity implements Serializable{

	private List<WyFwApp> house;
	private String fwyzYezhu;//业主id
	private int fwId;//房屋id
	private String fwAddr;//地址
	private String fwJiaofangTime;//交房时间
	private String fwLoupanName;//楼盘名称
	private String fwZhuangtai;//房屋状态
	private String fwShouFangTime;//收房时间
	
	private int jfWyIsLateFees;//是否需缴纳滞纳金 0是；1否
	private String jfWyTypeFwLeixing;//房屋类型id
	private Double jfWyTypeFeiyong;//每平米费用
	private String jfWyTypeName;//缴费类型名称
	private String jfWyTypeLateFees;//滞纳金比例
	private String jfFwTypeLeixing;//房屋类型名称
	private String jfWyUseEndTime;//使用截止时间
	
	private double arrearages;//欠费金额
	private double arrearLateFees;//欠费滞纳金额
	private int arrearMonth;//欠费月数

	public int getArrearMonth() {
		return arrearMonth;
	}

	public void setArrearMonth(int arrearMonth) {
		this.arrearMonth = arrearMonth;
	}

	public WyFwApp() {
	}
	public List<WyFwApp> getHouse() {
		return house;
	}

	public void setHouse(List<WyFwApp> house) {
		this.house = house;
	}

	public String getFwyzYezhu() {
		return fwyzYezhu;
	}
	public void setFwyzYezhu(String fwyzYezhu) {
		this.fwyzYezhu = fwyzYezhu;
	}
	public int getFwId() {
		return fwId;
	}
	public void setFwId(int fwId) {
		this.fwId = fwId;
	}
	public String getFwAddr() {
		return fwAddr;
	}
	public void setFwAddr(String fwAddr) {
		this.fwAddr = fwAddr;
	}
	public String getFwJiaofangTime() {
		return fwJiaofangTime;
	}
	public void setFwJiaofangTime(String fwJiaofangTime) {
		this.fwJiaofangTime = fwJiaofangTime;
	}
	public String getFwLoupanName() {
		return fwLoupanName;
	}
	public void setFwLoupanName(String fwLoupanName) {
		this.fwLoupanName = fwLoupanName;
	}
	public String getFwZhuangtai() {
		return fwZhuangtai;
	}
	public void setFwZhuangtai(String fwZhuangtai) {
		this.fwZhuangtai = fwZhuangtai;
	}
	public String getFwShouFangTime() {
		return fwShouFangTime;
	}
	public void setFwShouFangTime(String fwShouFangTime) {
		this.fwShouFangTime = fwShouFangTime;
	}
	public int getJfWyIsLateFees() {
		return jfWyIsLateFees;
	}
	public void setJfWyIsLateFees(int jfWyIsLateFees) {
		this.jfWyIsLateFees = jfWyIsLateFees;
	}
	public String getJfWyTypeFwLeixing() {
		return jfWyTypeFwLeixing;
	}
	public void setJfWyTypeFwLeixing(String jfWyTypeFwLeixing) {
		this.jfWyTypeFwLeixing = jfWyTypeFwLeixing;
	}
	public Double getJfWyTypeFeiyong() {
		return jfWyTypeFeiyong;
	}
	public void setJfWyTypeFeiyong(Double jfWyTypeFeiyong) {
		this.jfWyTypeFeiyong = jfWyTypeFeiyong;
	}
	public String getJfWyTypeName() {
		return jfWyTypeName;
	}
	public void setJfWyTypeName(String jfWyTypeName) {
		this.jfWyTypeName = jfWyTypeName;
	}
	public String getJfWyTypeLateFees() {
		return jfWyTypeLateFees;
	}
	public void setJfWyTypeLateFees(String jfWyTypeLateFees) {
		this.jfWyTypeLateFees = jfWyTypeLateFees;
	}
	public String getJfFwTypeLeixing() {
		return jfFwTypeLeixing;
	}
	public void setJfFwTypeLeixing(String jfFwTypeLeixing) {
		this.jfFwTypeLeixing = jfFwTypeLeixing;
	}
	public String getJfWyUseEndTime() {
		return jfWyUseEndTime;
	}
	public void setJfWyUseEndTime(String jfWyUseEndTime) {
		this.jfWyUseEndTime = jfWyUseEndTime;
	}
	public double getArrearages() {
		return arrearages;
	}
	public void setArrearages(double arrearages) {
		this.arrearages = arrearages;
	}
	public double getArrearLateFees() {
		return arrearLateFees;
	}
	public void setArrearLateFees(double arrearLateFees) {
		this.arrearLateFees = arrearLateFees;
	}
	
}
