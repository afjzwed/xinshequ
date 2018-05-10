package com.yxld.yxchuangxin.entity;



/**
 * CxwyMallUser entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CxwyMallUser  implements java.io.Serializable {

    // Fields    
	 private Integer userId;//用户id
     private String userUserName;//用户名
     private String userPassWord;//密码
     private String userTel;//电话
     private String userIntegral;//余额
     private String userIdCard;//身份证号码
     private String userSpare1;//所属在小区
     private String userSpare2;//别用项目
     private String userSpare3; //默认地址
     private String userSpare4;


    // Constructors

    /** default constructor */
    public CxwyMallUser() {
    }

	/** minimal constructor */
    public CxwyMallUser(String userUserName, String userPassWord) {
        this.userUserName = userUserName;
        this.userPassWord = userPassWord;
    }
    
    /** full constructor */
    public CxwyMallUser(String userUserName, String userPassWord, String userTel, String userIntegral, String userIdCard, String userSpare1, String userSpare2, String userSpare3, String userSpare4) {
        this.userUserName = userUserName;
        this.userPassWord = userPassWord;
        this.userTel = userTel;
        this.userIntegral = userIntegral;
        this.userIdCard = userIdCard;
        this.userSpare1 = userSpare1;
        this.userSpare2 = userSpare2;
        this.userSpare3 = userSpare3;
        this.userSpare4 = userSpare4;
    }

   
    // Property accessors

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserUserName() {
        return this.userUserName;
    }
    
    public void setUserUserName(String userUserName) {
        this.userUserName = userUserName;
    }

    public String getUserPassWord() {
        return this.userPassWord;
    }
    
    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    public String getUserTel() {
        return this.userTel;
    }
    
    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserIntegral() {
        return this.userIntegral;
    }
    
    public void setUserIntegral(String userIntegral) {
        this.userIntegral = userIntegral;
    }

    public String getUserIdCard() {
        return this.userIdCard;
    }
    
    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard;
    }

    public String getUserSpare1() {
        return this.userSpare1;
    }
    
    public void setUserSpare1(String userSpare1) {
        this.userSpare1 = userSpare1;
    }

    public String getUserSpare2() {
        return this.userSpare2;
    }
    
    public void setUserSpare2(String userSpare2) {
        this.userSpare2 = userSpare2;
    }

    public String getUserSpare3() {
        return this.userSpare3;
    }
    
    public void setUserSpare3(String userSpare3) {
        this.userSpare3 = userSpare3;
    }

    public String getUserSpare4() {
        return this.userSpare4;
    }
    
    public void setUserSpare4(String userSpare4) {
        this.userSpare4 = userSpare4;
    }

	@Override
	public String toString() {
		return "CxwyMallUser [userId=" + userId + ", userUserName="
				+ userUserName + ", userPassWord=" + userPassWord
				+ ", userTel=" + userTel + ", userIntegral=" + userIntegral
				+ ", userIdCard=" + userIdCard + ", userSpare1=" + userSpare1
				+ ", userSpare2=" + userSpare2 + ", userSpare3=" + userSpare3
				+ ", userSpare4=" + userSpare4 + "]";
	}

}