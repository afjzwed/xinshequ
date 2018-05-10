package com.yxld.yxchuangxin.entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * CxwyMallAdd entity. @author MyEclipse Persistence Tools
 */

public class CxwyMallAdd extends BaseEntity implements Serializable {
	
	private List<CxwyMallAdd> addList;

	// Fields
	private Integer addId;
	private String addName;//联系人
	private String addVillage="";//小区
	private String addTel;//电话
	private String addAdd="";//详细地址
	private String addUserName;//用户名
	private Integer addStatus;//状态位，设置为默认地址,0为默认地址，1不位默认地址
	private String addSpare1 ="";//省市区
	private String addSpare2;
	private String addSpare3;
	private String addSpare4;

	// Constructors

	/** default constructor */
	public CxwyMallAdd() {
	}

	/** full constructor */
	public CxwyMallAdd(String addName, String addVillage, String addTel,
                       String addAdd, String addUserName, Integer addStatus,
                       String addSpare1, String addSpare2, String addSpare3,
                       String addSpare4) {
		this.addName = addName;
		this.addVillage = addVillage;
		this.addTel = addTel;
		this.addAdd = addAdd;
		this.addUserName = addUserName;
		this.addStatus = addStatus;
		this.addSpare1 = addSpare1;
		this.addSpare2 = addSpare2;
		this.addSpare3 = addSpare3;
		this.addSpare4 = addSpare4;
	}

	// Property accessors



	public Integer getAddId() {
		return this.addId;
	}

	public void setAddId(Integer addId) {
		this.addId = addId;
	}

	public String getAddName() {
		return this.addName;
	}

	public void setAddName(String addName) {
		this.addName = addName;
	}

	public String getAddVillage() {
		return this.addVillage;
	}

	public void setAddVillage(String addVillage) {
		this.addVillage = addVillage;
	}

	public String getAddTel() {
		return this.addTel;
	}

	public void setAddTel(String addTel) {
		this.addTel = addTel;
	}

	public String getAddAdd() {
		return this.addAdd;
	}

	public void setAddAdd(String addAdd) {
		this.addAdd = addAdd;
	}

	public String getAddUserName() {
		return this.addUserName;
	}

	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}

	public Integer getAddStatus() {
		return this.addStatus;
	}

	public void setAddStatus(Integer addStatus) {
		this.addStatus = addStatus;
	}

	public String getAddSpare1() {
		return this.addSpare1;
	}

	public void setAddSpare1(String addSpare1) {
		this.addSpare1 = addSpare1;
	}

	public String getAddSpare2() {
		return this.addSpare2;
	}

	public void setAddSpare2(String addSpare2) {
		this.addSpare2 = addSpare2;
	}

	public String getAddSpare3() {
		return this.addSpare3;
	}

	public void setAddSpare3(String addSpare3) {
		this.addSpare3 = addSpare3;
	}

	public String getAddSpare4() {
		return this.addSpare4;
	}

	public void setAddSpare4(String addSpare4) {
		this.addSpare4 = addSpare4;
	}
	
	public List<CxwyMallAdd> getAddList() {
		return addList;
	}

	public void setAddList(List<CxwyMallAdd> addList) {
		this.addList = addList;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CxwyMallAdd that = (CxwyMallAdd) o;

		if (addId != null ? !addId.equals(that.addId) : that.addId != null) return false;
		if (addName != null ? !addName.equals(that.addName) : that.addName != null) return false;
		if (addVillage != null ? !addVillage.equals(that.addVillage) : that.addVillage != null)
			return false;
		if (addTel != null ? !addTel.equals(that.addTel) : that.addTel != null) return false;
		if (addAdd != null ? !addAdd.equals(that.addAdd) : that.addAdd != null) return false;
		if (addUserName != null ? !addUserName.equals(that.addUserName) : that.addUserName != null)
			return false;
		if (addStatus != null ? !addStatus.equals(that.addStatus) : that.addStatus != null)
			return false;
		if (addSpare1 != null ? !addSpare1.equals(that.addSpare1) : that.addSpare1 != null)
			return false;
		if (addSpare2 != null ? !addSpare2.equals(that.addSpare2) : that.addSpare2 != null)
			return false;
		if (addSpare3 != null ? !addSpare3.equals(that.addSpare3) : that.addSpare3 != null)
			return false;
		return addSpare4 != null ? addSpare4.equals(that.addSpare4) : that.addSpare4 == null;

	}

	@Override
	public int hashCode() {
		int result = addId != null ? addId.hashCode() : 0;
		result = 31 * result + (addName != null ? addName.hashCode() : 0);
		result = 31 * result + (addVillage != null ? addVillage.hashCode() : 0);
		result = 31 * result + (addTel != null ? addTel.hashCode() : 0);
		result = 31 * result + (addAdd != null ? addAdd.hashCode() : 0);
		result = 31 * result + (addUserName != null ? addUserName.hashCode() : 0);
		result = 31 * result + (addStatus != null ? addStatus.hashCode() : 0);
		result = 31 * result + (addSpare1 != null ? addSpare1.hashCode() : 0);
		result = 31 * result + (addSpare2 != null ? addSpare2.hashCode() : 0);
		result = 31 * result + (addSpare3 != null ? addSpare3.hashCode() : 0);
		result = 31 * result + (addSpare4 != null ? addSpare4.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "CxwyMallAdd [addList=" + addList + ", addId=" + addId
				+ ", addName=" + addName + ", addVillage=" + addVillage
				+ ", addTel=" + addTel + ", addAdd=" + addAdd
				+ ", addUserName=" + addUserName + ", addStatus=" + addStatus
				+ ", addSpare1=" + addSpare1 + ", addSpare2=" + addSpare2
				+ ", addSpare3=" + addSpare3 + ", addSpare4=" + addSpare4
				+ ", status=" + status + ", MSG=" + MSG + "]";
	}
}