package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

/**
 * 作者：yishangfei on 2017/1/21 0021 11:17
 * 邮箱：yishangfei@foxmail.com
 */
public class APPCamera extends BaseEntity {

    /**
     * error_code : 0
     * UserID : -2141090270
     * P2PVerifyCode1 : 1608807042
     * P2PVerifyCode2 : 1974361212
     * Email :
     * NickName :
     * CountryCode :
     * PhoneNO :
     * ImageID :
     * SessionID : 1461388912
     * UserLevel :
     * AutoAllotID : 1
     * SessionID2 :
     */

    private String error_code;
    private String UserID;
    private String P2PVerifyCode1;
    private String P2PVerifyCode2;
    private String Email;
    private String NickName;
    private String CountryCode;
    private String PhoneNO;
    private String ImageID;
    private String SessionID;
    private String UserLevel;
    private String AutoAllotID;
    private String SessionID2;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getP2PVerifyCode1() {
        return P2PVerifyCode1;
    }

    public void setP2PVerifyCode1(String P2PVerifyCode1) {
        this.P2PVerifyCode1 = P2PVerifyCode1;
    }

    public String getP2PVerifyCode2() {
        return P2PVerifyCode2;
    }

    public void setP2PVerifyCode2(String P2PVerifyCode2) {
        this.P2PVerifyCode2 = P2PVerifyCode2;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String CountryCode) {
        this.CountryCode = CountryCode;
    }

    public String getPhoneNO() {
        return PhoneNO;
    }

    public void setPhoneNO(String PhoneNO) {
        this.PhoneNO = PhoneNO;
    }

    public String getImageID() {
        return ImageID;
    }

    public void setImageID(String ImageID) {
        this.ImageID = ImageID;
    }

    public String getSessionID() {
        return SessionID;
    }

    public void setSessionID(String SessionID) {
        this.SessionID = SessionID;
    }

    public String getUserLevel() {
        return UserLevel;
    }

    public void setUserLevel(String UserLevel) {
        this.UserLevel = UserLevel;
    }

    public String getAutoAllotID() {
        return AutoAllotID;
    }

    public void setAutoAllotID(String AutoAllotID) {
        this.AutoAllotID = AutoAllotID;
    }

    public String getSessionID2() {
        return SessionID2;
    }

    public void setSessionID2(String SessionID2) {
        this.SessionID2 = SessionID2;
    }

    @Override
    public String toString() {
        return "APPCamera{" +
                "error_code='" + error_code + '\'' +
                ", UserID='" + UserID + '\'' +
                ", P2PVerifyCode1='" + P2PVerifyCode1 + '\'' +
                ", P2PVerifyCode2='" + P2PVerifyCode2 + '\'' +
                ", Email='" + Email + '\'' +
                ", NickName='" + NickName + '\'' +
                ", CountryCode='" + CountryCode + '\'' +
                ", PhoneNO='" + PhoneNO + '\'' +
                ", ImageID='" + ImageID + '\'' +
                ", SessionID='" + SessionID + '\'' +
                ", UserLevel='" + UserLevel + '\'' +
                ", AutoAllotID='" + AutoAllotID + '\'' +
                ", SessionID2='" + SessionID2 + '\'' +
                '}';
    }
}
