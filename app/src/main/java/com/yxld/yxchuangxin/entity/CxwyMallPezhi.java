package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * @author wwx
 * 商城配置类
 */
public class CxwyMallPezhi extends BaseEntity implements java.io.Serializable  {

	private List<CxwyMallPezhi> lblist;
	private List<CxwyMallPezhi> tblist;

	// Fields
	private Integer mallPezhiId;
	/** 配置key */
	private String mallPeizhiKey;
	/** 配置value */
	private String mallPeizhiValue;
	/** 配置说明 */
	private String mallPeizhiShuoming;
	/** 配置类型 */
	private String mallPeizhiType;
	/** 配置外部链接 */
	private String mallPeizhiUrl;
	/** 配置备用字段 */
	private String mallPeizhiBeiyong;

	// Constructors

	/** default constructor */
	public CxwyMallPezhi() {
	}

	/** full constructor */
	public CxwyMallPezhi(String mallPeizhiKey, String mallPeizhiValue,
                         String mallPeizhiShuoming, String mallPeizhiType,
                         String mallPeizhiUrl, String mallPeizhiBeiyong) {
		this.mallPeizhiKey = mallPeizhiKey;
		this.mallPeizhiValue = mallPeizhiValue;
		this.mallPeizhiShuoming = mallPeizhiShuoming;
		this.mallPeizhiType = mallPeizhiType;
		this.mallPeizhiUrl = mallPeizhiUrl;
		this.mallPeizhiBeiyong = mallPeizhiBeiyong;
	}

	// Property accessors

	public Integer getMallPezhiId() {
		return this.mallPezhiId;
	}

	public void setMallPezhiId(Integer mallPezhiId) {
		this.mallPezhiId = mallPezhiId;
	}

	public String getMallPeizhiKey() {
		return this.mallPeizhiKey;
	}

	public void setMallPeizhiKey(String mallPeizhiKey) {
		this.mallPeizhiKey = mallPeizhiKey;
	}

	public String getMallPeizhiValue() {
		return this.mallPeizhiValue;
	}

	public void setMallPeizhiValue(String mallPeizhiValue) {
		this.mallPeizhiValue = mallPeizhiValue;
	}

	public String getMallPeizhiShuoming() {
		return this.mallPeizhiShuoming;
	}

	public void setMallPeizhiShuoming(String mallPeizhiShuoming) {
		this.mallPeizhiShuoming = mallPeizhiShuoming;
	}

	public String getMallPeizhiType() {
		return this.mallPeizhiType;
	}

	public void setMallPeizhiType(String mallPeizhiType) {
		this.mallPeizhiType = mallPeizhiType;
	}

	public String getMallPeizhiUrl() {
		return this.mallPeizhiUrl;
	}

	public void setMallPeizhiUrl(String mallPeizhiUrl) {
		this.mallPeizhiUrl = mallPeizhiUrl;
	}

	public String getMallPeizhiBeiyong() {
		return this.mallPeizhiBeiyong;
	}

	public void setMallPeizhiBeiyong(String mallPeizhiBeiyong) {
		this.mallPeizhiBeiyong = mallPeizhiBeiyong;
	}

	public List<CxwyMallPezhi> getLblist() {
		return lblist;
	}

	public void setLblist(List<CxwyMallPezhi> lblist) {
		this.lblist = lblist;
	}

	public List<CxwyMallPezhi> getTblist() {
		return tblist;
	}

	public void setTblist(List<CxwyMallPezhi> tblist) {
		this.tblist = tblist;
	}

	@Override
	public String toString() {
		return "CxwyMallPezhi{" +
				"lblist=" + lblist +
				", tblist=" + tblist +
				", mallPezhiId=" + mallPezhiId +
				", mallPeizhiKey='" + mallPeizhiKey + '\'' +
				", mallPeizhiValue='" + mallPeizhiValue + '\'' +
				", mallPeizhiShuoming='" + mallPeizhiShuoming + '\'' +
				", mallPeizhiType='" + mallPeizhiType + '\'' +
				", mallPeizhiUrl='" + mallPeizhiUrl + '\'' +
				", mallPeizhiBeiyong='" + mallPeizhiBeiyong + '\'' +
				'}';
	}
}