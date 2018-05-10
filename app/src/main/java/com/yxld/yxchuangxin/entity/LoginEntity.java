package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

public class LoginEntity extends BaseEntity {

	/**
	 * MSG : 登录成功
	 * addr : {"addAdd":"中远1号","addId":430,"addName":"胡智鹏","addSpare1":"","addSpare2":"2218","addSpare3":"","addSpare4":"","addStatus":0,"addTel":"18670819116","addUserName":"胡智鹏","addVillage":"中远公馆"}
	 * house : [{"fwDanyuan":"1","fwFanghao":"301","fwHuxing":"111","fwId":14,"fwLoudong":"1","fwLoupanId":346,"fwMainji":"2","fwyzType":1,"xiangmuLoupan":"中远公馆","xiangmuTelphone":"123456789","yezhuId":2218,"yezhuName":"胡智鹏"}]
	 * status : 0
	 * user : {"yezhuBeizhu":"","yezhuBiezhu1":"2017-05-02 11:42:18","yezhuCardNum":"430725199000000000","yezhuChuangxinhao":"","yezhuGzdw":"Android","yezhuId":2218,"yezhuIsUse":0,"yezhuLoupan":"","yezhuName":"胡智鹏","yezhuPhone":"","yezhuPwd":"96E79218965EB72C92A549DD5A330112","yezhuSex":"0","yezhuSfzSrc1":"","yezhuSfzSrc2":"","yezhuShouji":"18670819116","yezhuType":0,"yezhuTypeValue":"","yezhuXmId":346}
	 * uuid : 9154ec05-4dea-45da-b4e9-751cfc38b238
	 */

	private CxwyMallAdd addr;
	private CxwyYezhu user;
	private String uuid;
	private List<AppYezhuFangwu> house;

	public String getMSG() {
		return MSG;
	}

	public void setMSG(String MSG) {
		this.MSG = MSG;
	}

	public CxwyMallAdd getAddr() {
		return addr;
	}

	public void setAddr(CxwyMallAdd addr) {
		this.addr = addr;
	}

	public CxwyYezhu getUser() {
		return user;
	}

	public void setUser(CxwyYezhu user) {
		this.user = user;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public List<AppYezhuFangwu> getHouse() {
		return house;
	}

	public void setHouse(List<AppYezhuFangwu> house) {
		this.house = house;
	}

	public static class AddrBean {
		/**
		 * addAdd : 中远1号
		 * addId : 430
		 * addName : 胡智鹏
		 * addSpare1 :
		 * addSpare2 : 2218
		 * addSpare3 :
		 * addSpare4 :
		 * addStatus : 0
		 * addTel : 18670819116
		 * addUserName : 胡智鹏
		 * addVillage : 中远公馆
		 */

		private String addAdd;
		private int addId;
		private String addName;
		private String addSpare1;
		private String addSpare2;
		private String addSpare3;
		private String addSpare4;
		private int addStatus;
		private String addTel;
		private String addUserName;
		private String addVillage;

		public String getAddAdd() {
			return addAdd;
		}

		public void setAddAdd(String addAdd) {
			this.addAdd = addAdd;
		}

		public int getAddId() {
			return addId;
		}

		public void setAddId(int addId) {
			this.addId = addId;
		}

		public String getAddName() {
			return addName;
		}

		public void setAddName(String addName) {
			this.addName = addName;
		}

		public String getAddSpare1() {
			return addSpare1;
		}

		public void setAddSpare1(String addSpare1) {
			this.addSpare1 = addSpare1;
		}

		public String getAddSpare2() {
			return addSpare2;
		}

		public void setAddSpare2(String addSpare2) {
			this.addSpare2 = addSpare2;
		}

		public String getAddSpare3() {
			return addSpare3;
		}

		public void setAddSpare3(String addSpare3) {
			this.addSpare3 = addSpare3;
		}

		public String getAddSpare4() {
			return addSpare4;
		}

		public void setAddSpare4(String addSpare4) {
			this.addSpare4 = addSpare4;
		}

		public int getAddStatus() {
			return addStatus;
		}

		public void setAddStatus(int addStatus) {
			this.addStatus = addStatus;
		}

		public String getAddTel() {
			return addTel;
		}

		public void setAddTel(String addTel) {
			this.addTel = addTel;
		}

		public String getAddUserName() {
			return addUserName;
		}

		public void setAddUserName(String addUserName) {
			this.addUserName = addUserName;
		}

		public String getAddVillage() {
			return addVillage;
		}

		public void setAddVillage(String addVillage) {
			this.addVillage = addVillage;
		}
	}

	public static class UserBean {
		/**
		 * yezhuBeizhu :
		 * yezhuBiezhu1 : 2017-05-02 11:42:18
		 * yezhuCardNum : 430725199000000000
		 * yezhuChuangxinhao :
		 * yezhuGzdw : Android
		 * yezhuId : 2218
		 * yezhuIsUse : 0
		 * yezhuLoupan :
		 * yezhuName : 胡智鹏
		 * yezhuPhone :
		 * yezhuPwd : 96E79218965EB72C92A549DD5A330112
		 * yezhuSex : 0
		 * yezhuSfzSrc1 :
		 * yezhuSfzSrc2 :
		 * yezhuShouji : 18670819116
		 * yezhuType : 0
		 * yezhuTypeValue :
		 * yezhuXmId : 346
		 */

		private String yezhuBeizhu;
		private String yezhuBiezhu1;
		private String yezhuCardNum;
		private String yezhuChuangxinhao;
		private String yezhuGzdw;
		private int yezhuId;
		private int yezhuIsUse;
		private String yezhuLoupan;
		private String yezhuName;
		private String yezhuPhone;
		private String yezhuPwd;
		private String yezhuSex;
		private String yezhuSfzSrc1;
		private String yezhuSfzSrc2;
		private String yezhuShouji;
		private int yezhuType;
		private String yezhuTypeValue;
		private int yezhuXmId;

		public String getYezhuBeizhu() {
			return yezhuBeizhu;
		}

		public void setYezhuBeizhu(String yezhuBeizhu) {
			this.yezhuBeizhu = yezhuBeizhu;
		}

		public String getYezhuBiezhu1() {
			return yezhuBiezhu1;
		}

		public void setYezhuBiezhu1(String yezhuBiezhu1) {
			this.yezhuBiezhu1 = yezhuBiezhu1;
		}

		public String getYezhuCardNum() {
			return yezhuCardNum;
		}

		public void setYezhuCardNum(String yezhuCardNum) {
			this.yezhuCardNum = yezhuCardNum;
		}

		public String getYezhuChuangxinhao() {
			return yezhuChuangxinhao;
		}

		public void setYezhuChuangxinhao(String yezhuChuangxinhao) {
			this.yezhuChuangxinhao = yezhuChuangxinhao;
		}

		public String getYezhuGzdw() {
			return yezhuGzdw;
		}

		public void setYezhuGzdw(String yezhuGzdw) {
			this.yezhuGzdw = yezhuGzdw;
		}

		public int getYezhuId() {
			return yezhuId;
		}

		public void setYezhuId(int yezhuId) {
			this.yezhuId = yezhuId;
		}

		public int getYezhuIsUse() {
			return yezhuIsUse;
		}

		public void setYezhuIsUse(int yezhuIsUse) {
			this.yezhuIsUse = yezhuIsUse;
		}

		public String getYezhuLoupan() {
			return yezhuLoupan;
		}

		public void setYezhuLoupan(String yezhuLoupan) {
			this.yezhuLoupan = yezhuLoupan;
		}

		public String getYezhuName() {
			return yezhuName;
		}

		public void setYezhuName(String yezhuName) {
			this.yezhuName = yezhuName;
		}

		public String getYezhuPhone() {
			return yezhuPhone;
		}

		public void setYezhuPhone(String yezhuPhone) {
			this.yezhuPhone = yezhuPhone;
		}

		public String getYezhuPwd() {
			return yezhuPwd;
		}

		public void setYezhuPwd(String yezhuPwd) {
			this.yezhuPwd = yezhuPwd;
		}

		public String getYezhuSex() {
			return yezhuSex;
		}

		public void setYezhuSex(String yezhuSex) {
			this.yezhuSex = yezhuSex;
		}

		public String getYezhuSfzSrc1() {
			return yezhuSfzSrc1;
		}

		public void setYezhuSfzSrc1(String yezhuSfzSrc1) {
			this.yezhuSfzSrc1 = yezhuSfzSrc1;
		}

		public String getYezhuSfzSrc2() {
			return yezhuSfzSrc2;
		}

		public void setYezhuSfzSrc2(String yezhuSfzSrc2) {
			this.yezhuSfzSrc2 = yezhuSfzSrc2;
		}

		public String getYezhuShouji() {
			return yezhuShouji;
		}

		public void setYezhuShouji(String yezhuShouji) {
			this.yezhuShouji = yezhuShouji;
		}

		public int getYezhuType() {
			return yezhuType;
		}

		public void setYezhuType(int yezhuType) {
			this.yezhuType = yezhuType;
		}

		public String getYezhuTypeValue() {
			return yezhuTypeValue;
		}

		public void setYezhuTypeValue(String yezhuTypeValue) {
			this.yezhuTypeValue = yezhuTypeValue;
		}

		public int getYezhuXmId() {
			return yezhuXmId;
		}

		public void setYezhuXmId(int yezhuXmId) {
			this.yezhuXmId = yezhuXmId;
		}
	}

	public static class HouseBean {
		/**
		 * fwDanyuan : 1
		 * fwFanghao : 301
		 * fwHuxing : 111
		 * fwId : 14
		 * fwLoudong : 1
		 * fwLoupanId : 346
		 * fwMainji : 2
		 * fwyzType : 1
		 * xiangmuLoupan : 中远公馆
		 * xiangmuTelphone : 123456789
		 * yezhuId : 2218
		 * yezhuName : 胡智鹏
		 */

		private String fwDanyuan;
		private String fwFanghao;
		private String fwHuxing;
		private int fwId;
		private String fwLoudong;
		private int fwLoupanId;
		private String fwMainji;
		private int fwyzType;
		private String xiangmuLoupan;
		private String xiangmuTelphone;
		private int yezhuId;
		private String yezhuName;

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

		public int getFwLoupanId() {
			return fwLoupanId;
		}

		public void setFwLoupanId(int fwLoupanId) {
			this.fwLoupanId = fwLoupanId;
		}

		public String getFwMainji() {
			return fwMainji;
		}

		public void setFwMainji(String fwMainji) {
			this.fwMainji = fwMainji;
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

		public int getYezhuId() {
			return yezhuId;
		}

		public void setYezhuId(int yezhuId) {
			this.yezhuId = yezhuId;
		}

		public String getYezhuName() {
			return yezhuName;
		}

		public void setYezhuName(String yezhuName) {
			this.yezhuName = yezhuName;
		}
	}
}
