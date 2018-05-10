package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * @ClassName: CxwyMallUserBalance 
 * @Description: 余额记录表
 * @author wwx
 * @date 2016-9-7 上午11:57:10 
 */
public class CxwyMallUserBalance extends BaseEntity {

	private List<CxwyMallUserBalance> rows;

	public List<CxwyMallUserBalance> getRows() {
		return rows;
	}

	public void setRows(List<CxwyMallUserBalance> rows) {
		this.rows = rows;
	}

	// Fields
	private Integer balanceId;
	/** 用户id*/
	private Integer balanceUserId;
	/** 操作时间*/
	private String balanceTime;
	/** 金额*/
	private String balanceJine;
	/** 类型   支出、充值*/
	private String balanceType;
	/** 充值方式  支付宝、微信、银联*/
	private String balancePayment;
	/** 消费 商品名称*/
	private String balanceGoodsname;
	/** 消费 订单编号*/
	private String balanceOrderbianhao;
	private String balanceBeiyong1;
	private String balanceBeiyong2;

	// Constructors

	/** default constructor */
	public CxwyMallUserBalance() {
	}

	/** full constructor */
	public CxwyMallUserBalance(Integer balanceUserId, String balanceTime,
                               String balanceJine, String balanceType, String balancePayment,
                               String balanceGoodsname, String balanceOrderbianhao,
                               String balanceBeiyong1, String balanceBeiyong2) {
		this.balanceUserId = balanceUserId;
		this.balanceTime = balanceTime;
		this.balanceJine = balanceJine;
		this.balanceType = balanceType;
		this.balancePayment = balancePayment;
		this.balanceGoodsname = balanceGoodsname;
		this.balanceOrderbianhao = balanceOrderbianhao;
		this.balanceBeiyong1 = balanceBeiyong1;
		this.balanceBeiyong2 = balanceBeiyong2;
	}

	// Property accessors

	public Integer getBalanceId() {
		return this.balanceId;
	}

	public void setBalanceId(Integer balanceId) {
		this.balanceId = balanceId;
	}

	public Integer getBalanceUserId() {
		return this.balanceUserId;
	}

	public void setBalanceUserId(Integer balanceUserId) {
		this.balanceUserId = balanceUserId;
	}

	public String getBalanceTime() {
		return this.balanceTime;
	}

	public void setBalanceTime(String balanceTime) {
		this.balanceTime = balanceTime;
	}

	public String getBalanceJine() {
		return this.balanceJine;
	}

	public void setBalanceJine(String balanceJine) {
		this.balanceJine = balanceJine;
	}

	public String getBalanceType() {
		return this.balanceType;
	}

	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}

	public String getBalancePayment() {
		return this.balancePayment;
	}

	public void setBalancePayment(String balancePayment) {
		this.balancePayment = balancePayment;
	}

	public String getBalanceGoodsname() {
		return this.balanceGoodsname;
	}

	public void setBalanceGoodsname(String balanceGoodsname) {
		this.balanceGoodsname = balanceGoodsname;
	}

	public String getBalanceOrderbianhao() {
		return this.balanceOrderbianhao;
	}

	public void setBalanceOrderbianhao(String balanceOrderbianhao) {
		this.balanceOrderbianhao = balanceOrderbianhao;
	}

	public String getBalanceBeiyong1() {
		return this.balanceBeiyong1;
	}

	public void setBalanceBeiyong1(String balanceBeiyong1) {
		this.balanceBeiyong1 = balanceBeiyong1;
	}

	public String getBalanceBeiyong2() {
		return this.balanceBeiyong2;
	}

	public void setBalanceBeiyong2(String balanceBeiyong2) {
		this.balanceBeiyong2 = balanceBeiyong2;
	}

	@Override
	public String toString() {
		return "CxwyMallUserBalance [balanceBeiyong1=" + balanceBeiyong1
				+ ", balanceBeiyong2=" + balanceBeiyong2
				+ ", balanceGoodsname=" + balanceGoodsname + ", balanceId="
				+ balanceId + ", balanceJine=" + balanceJine
				+ ", balanceOrderbianhao=" + balanceOrderbianhao
				+ ", balancePayment=" + balancePayment + ", balanceTime="
				+ balanceTime + ", balanceType=" + balanceType
				+ ", balanceUserId=" + balanceUserId + "]";
	}

}