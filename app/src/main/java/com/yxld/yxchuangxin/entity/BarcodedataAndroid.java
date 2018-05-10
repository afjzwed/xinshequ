package com.yxld.yxchuangxin.entity;

import java.util.List;

/**
 * Barcodate entity. @author MyEclipse Persistence Tools
 */

public class BarcodedataAndroid implements java.io.Serializable {

	private List<BarcodedataAndroid> rows;
	private  int total;


	// Fields
	private String name;
	private String dianhua;
	private String luopan;
	private Integer yezhuId;

	// Constructors

	/** default constructor */
	public BarcodedataAndroid() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDianhua() {
		return dianhua;
	}

	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}

	public String getLuopan() {
		return luopan;
	}

	public void setLuopan(String luopan) {
		this.luopan = luopan;
	}

	public Integer getYezhuId() {
		return yezhuId;
	}

	public void setYezhuId(Integer yezhuId) {
		this.yezhuId = yezhuId;
	}

	public List<BarcodedataAndroid> getRows() {
		return rows;
	}

	public void setRows(List<BarcodedataAndroid> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public BarcodedataAndroid(String name, String dianhua, String luopan,
                              Integer yezhuId) {
		super();
		this.name = name;
		this.dianhua = dianhua;
		this.luopan = luopan;
		this.yezhuId = yezhuId;
	}

	@Override
	public String toString() {
		return "BarcodedataAndroid{" +
				"rows=" + rows +
				", total=" + total +
				", name='" + name + '\'' +
				", dianhua='" + dianhua + '\'' +
				", luopan='" + luopan + '\'' +
				", yezhuId=" + yezhuId +
				'}';
	}
}