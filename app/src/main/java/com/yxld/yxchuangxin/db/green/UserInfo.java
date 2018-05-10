package com.yxld.yxchuangxin.db.green;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：Android on 2017/10/11
 * 邮箱：365941593@qq.com
 * 描述：
 */
@Entity
public class UserInfo {
    @Id
    private Long id;
    private String phoneNumber;
    private boolean isLogin;
    private String louPan;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getXiangmuId() {
        return xiangmuId;
    }

    public void setXiangmuId(int xiangmuId) {
        this.xiangmuId = xiangmuId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getIsLogin() {
        return this.isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public String getLouPan() {
        return this.louPan;
    }

    public void setLouPan(String louPan) {
        this.louPan = louPan;
    }

    private String password;
    private int xiangmuId;

    @Generated(hash = 1278202133)
    public UserInfo(Long id, String phoneNumber, boolean isLogin, String louPan,
            String password, int xiangmuId) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.isLogin = isLogin;
        this.louPan = louPan;
        this.password = password;
        this.xiangmuId = xiangmuId;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isLogin=" + isLogin +
                ", louPan='" + louPan + '\'' +
                ", password='" + password + '\'' +
                ", xiangmuId=" + xiangmuId +
                '}';
    }
}
