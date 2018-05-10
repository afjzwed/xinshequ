package com.yxld.yxchuangxin.entity;

/**
 * CxwyMallCollection entity. @author MyEclipse Persistence Tools
 */

public class CxwyMallCollection implements java.io.Serializable {

	// Fields

	private Integer collectionId;//id
	private Integer collectionShangpId;//商品id
	private String collectionShangpName;//商品名称
	private Integer collectionUserId;//用户id
	private String collectionSpare1;//备用字段
	private String collectionSpare2;
	private String collectionSpare3;
	private String collectionSpare4;
	private String collectionShangpOneRmb;//商品单价
	private String collectionShangpSpec;//规格
	private String collectionImgSrc;//图片

	// Constructors

	/** default constructor */
	public CxwyMallCollection() {
	}

	/** full constructor */
	public CxwyMallCollection(Integer collectionShangpId,
                              String collectionShangpName, Integer collectionUserId,
                              String collectionSpare1, String collectionSpare2,
                              String collectionSpare3, String collectionSpare4,
                              String collectionShangpOneRmb, String collectionShangpSpec,
                              String collectionImgSrc) {
		this.collectionShangpId = collectionShangpId;
		this.collectionShangpName = collectionShangpName;
		this.collectionUserId = collectionUserId;
		this.collectionSpare1 = collectionSpare1;
		this.collectionSpare2 = collectionSpare2;
		this.collectionSpare3 = collectionSpare3;
		this.collectionSpare4 = collectionSpare4;
		this.collectionShangpOneRmb = collectionShangpOneRmb;
		this.collectionShangpSpec = collectionShangpSpec;
		this.collectionImgSrc = collectionImgSrc;
	}

	// Property accessors

	public Integer getCollectionId() {
		return this.collectionId;
	}

	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}

	public Integer getCollectionShangpId() {
		return this.collectionShangpId;
	}

	public void setCollectionShangpId(Integer collectionShangpId) {
		this.collectionShangpId = collectionShangpId;
	}

	public String getCollectionShangpName() {
		return this.collectionShangpName;
	}

	public void setCollectionShangpName(String collectionShangpName) {
		this.collectionShangpName = collectionShangpName;
	}

	public Integer getCollectionUserId() {
		return this.collectionUserId;
	}

	public void setCollectionUserId(Integer collectionUserId) {
		this.collectionUserId = collectionUserId;
	}

	public String getCollectionSpare1() {
		return this.collectionSpare1;
	}

	public void setCollectionSpare1(String collectionSpare1) {
		this.collectionSpare1 = collectionSpare1;
	}

	public String getCollectionSpare2() {
		return this.collectionSpare2;
	}

	public void setCollectionSpare2(String collectionSpare2) {
		this.collectionSpare2 = collectionSpare2;
	}

	public String getCollectionSpare3() {
		return this.collectionSpare3;
	}

	public void setCollectionSpare3(String collectionSpare3) {
		this.collectionSpare3 = collectionSpare3;
	}

	public String getCollectionSpare4() {
		return this.collectionSpare4;
	}

	public void setCollectionSpare4(String collectionSpare4) {
		this.collectionSpare4 = collectionSpare4;
	}

	public String getCollectionShangpOneRmb() {
		return this.collectionShangpOneRmb;
	}

	public void setCollectionShangpOneRmb(String collectionShangpOneRmb) {
		this.collectionShangpOneRmb = collectionShangpOneRmb;
	}

	public String getCollectionShangpSpec() {
		return this.collectionShangpSpec;
	}

	public void setCollectionShangpSpec(String collectionShangpSpec) {
		this.collectionShangpSpec = collectionShangpSpec;
	}

	public String getCollectionImgSrc() {
		return this.collectionImgSrc;
	}

	public void setCollectionImgSrc(String collectionImgSrc) {
		this.collectionImgSrc = collectionImgSrc;
	}

}