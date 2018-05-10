package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * CxwyYezhu entity. @author Carrol
 */

public class CxwyYezhu extends BaseEntity implements java.io.Serializable{

	private List<CxwyYezhu> rows;

	// Fields
	private Integer yezhuId; //业主id
	private String yezhuCardNum="";//身份证
	private String yezhuName="";//业主姓名
	private String yezhuSex="";//性别 业主性别：0男1女
	private String yezhuPhone="";//电话
	private String yezhuShouji="";//手机
	private String yezhuSfzSrc1="";//身份证照片
	private String yezhuSfzSrc2="";//身份证照片
	private String yezhuGzdw="";//工作单位
	private String yezhuBeizhu="";//备注
	private Integer yezhuIsUse;//是否使用
	private String yezhuPwd="";//密码
	private Integer yezhuType;//业主类型：0业主 1 游客
	private String yezhuBiezhu1="";//添加时间
	private String yezhuChuangxinhao; //业主创新号
	private Integer yezhuXmId;
	/** default constructor */
	public CxwyYezhu() {
	}

	public CxwyYezhu(Integer yezhuId, String yezhuCardNum, String yezhuName, String yezhuSex, String yezhuPhone, String yezhuShouji, String yezhuSfzSrc1, String yezhuSfzSrc2, String yezhuGzdw, String yezhuBeizhu, Integer yezhuIsUse, String yezhuPwd, Integer yezhuType, String yezhuBiezhu1) {
		this.yezhuId = yezhuId;
		this.yezhuCardNum = yezhuCardNum;
		this.yezhuName = yezhuName;
		this.yezhuSex = yezhuSex;
		this.yezhuPhone = yezhuPhone;
		this.yezhuShouji = yezhuShouji;
		this.yezhuSfzSrc1 = yezhuSfzSrc1;
		this.yezhuSfzSrc2 = yezhuSfzSrc2;
		this.yezhuGzdw = yezhuGzdw;
		this.yezhuBeizhu = yezhuBeizhu;
		this.yezhuIsUse = yezhuIsUse;
		this.yezhuPwd = yezhuPwd;
		this.yezhuType = yezhuType;
		this.yezhuBiezhu1 = yezhuBiezhu1;
	}

	public CxwyYezhu(Integer yezhuId, String yezhuCardNum, String yezhuName, String yezhuSex, String yezhuPhone, String yezhuShouji, String yezhuSfzSrc1, String yezhuSfzSrc2, String yezhuGzdw, String yezhuBeizhu, Integer yezhuIsUse, String yezhuPwd, Integer yezhuType, String yezhuBiezhu1, String yezhuChuangxinhao) {
		this.yezhuId = yezhuId;
		this.yezhuCardNum = yezhuCardNum;
		this.yezhuName = yezhuName;
		this.yezhuSex = yezhuSex;
		this.yezhuPhone = yezhuPhone;
		this.yezhuShouji = yezhuShouji;
		this.yezhuSfzSrc1 = yezhuSfzSrc1;
		this.yezhuSfzSrc2 = yezhuSfzSrc2;
		this.yezhuGzdw = yezhuGzdw;
		this.yezhuBeizhu = yezhuBeizhu;
		this.yezhuIsUse = yezhuIsUse;
		this.yezhuPwd = yezhuPwd;
		this.yezhuType = yezhuType;
		this.yezhuBiezhu1 = yezhuBiezhu1;
		this.yezhuChuangxinhao = yezhuChuangxinhao;
	}
	// Property accessors

	public Integer getYezhuId() {
		return this.yezhuId;
	}

	public void setYezhuId(Integer yezhuId) {
		this.yezhuId = yezhuId;
	}

	public String getYezhuCardNum() {
		return this.yezhuCardNum;
	}

	public void setYezhuCardNum(String yezhuCardNum) {
		this.yezhuCardNum = yezhuCardNum;
	}

	public String getYezhuName() {
		return this.yezhuName;
	}

	public void setYezhuName(String yezhuName) {
		this.yezhuName = yezhuName;
	}

	public String getYezhuSex() {
		return this.yezhuSex;
	}

	public void setYezhuSex(String yezhuSex) {
		this.yezhuSex = yezhuSex;
	}

	public String getYezhuPhone() {
		return this.yezhuPhone;
	}

	public void setYezhuPhone(String yezhuPhone) {
		this.yezhuPhone = yezhuPhone;
	}

	public String getYezhuShouji() {
		return this.yezhuShouji;
	}

	public void setYezhuShouji(String yezhuShouji) {
		this.yezhuShouji = yezhuShouji;
	}

	public String getYezhuSfzSrc1() {
		return this.yezhuSfzSrc1;
	}

	public void setYezhuSfzSrc1(String yezhuSfzSrc1) {
		this.yezhuSfzSrc1 = yezhuSfzSrc1;
	}

	public String getYezhuSfzSrc2() {
		return this.yezhuSfzSrc2;
	}

	public void setYezhuSfzSrc2(String yezhuSfzSrc2) {
		this.yezhuSfzSrc2 = yezhuSfzSrc2;
	}

	public String getYezhuBiezhu1() {
		return yezhuBiezhu1;
	}

	public void setYezhuBiezhu1(String yezhuBiezhu1) {
		this.yezhuBiezhu1 = yezhuBiezhu1;
	}

	public String getYezhuGzdw() {
		return this.yezhuGzdw;
	}

	public void setYezhuGzdw(String yezhuGzdw) {
		this.yezhuGzdw = yezhuGzdw;
	}

	public String getYezhuBeizhu() {
		return this.yezhuBeizhu;
	}

	public void setYezhuBeizhu(String yezhuBeizhu) {
		this.yezhuBeizhu = yezhuBeizhu;
	}

	public Integer getYezhuIsUse() {
		return yezhuIsUse;
	}

	public void setYezhuIsUse(Integer yezhuIsUse) {
		this.yezhuIsUse = yezhuIsUse;
	}


	public String getYezhuPwd() {
		return yezhuPwd;
	}

	public void setYezhuPwd(String yezhuPwd) {
		this.yezhuPwd = yezhuPwd;
	}

	public Integer getYezhuType() {
		return yezhuType;
	}

	public void setYezhuType(Integer yezhuType) {
		this.yezhuType = yezhuType;
	}

	public List<CxwyYezhu> getRows() {
		return rows;
	}

	public void setRows(List<CxwyYezhu> rows) {
		this.rows = rows;
	}

	public String getYezhuChuangxinhao() {
		return yezhuChuangxinhao;
	}

	public void setYezhuChuangxinhao(String yezhuChuangxinhao) {
		this.yezhuChuangxinhao = yezhuChuangxinhao;
	}

	public Integer getYezhuXmId() {
		return yezhuXmId;
	}

	public void setYezhuXmId(Integer yezhuXmId) {
		this.yezhuXmId = yezhuXmId;
	}

	@Override
	public String toString() {
		return "CxwyYezhu{" +
				"yezhuId=" + yezhuId +
				", yezhuCardNum='" + yezhuCardNum + '\'' +
				", yezhuName='" + yezhuName + '\'' +
				", yezhuSex='" + yezhuSex + '\'' +
				", yezhuPhone='" + yezhuPhone + '\'' +
				", yezhuShouji='" + yezhuShouji + '\'' +
				", yezhuSfzSrc1='" + yezhuSfzSrc1 + '\'' +
				", yezhuSfzSrc2='" + yezhuSfzSrc2 + '\'' +
				", yezhuGzdw='" + yezhuGzdw + '\'' +
				", yezhuBeizhu='" + yezhuBeizhu + '\'' +
				", yezhuIsUse=" + yezhuIsUse +
				", yezhuPwd='" + yezhuPwd + '\'' +
				", yezhuType=" + yezhuType +
				", yezhuBiezhu1='" + yezhuBiezhu1 + '\'' +
				", yezhuChuangxinhao='" + yezhuChuangxinhao + '\'' +
				'}';
	}
}