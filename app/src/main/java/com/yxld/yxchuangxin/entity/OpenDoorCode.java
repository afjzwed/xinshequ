package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

/**
 * wwx手机开门获取二维码构建对象
 */
public class OpenDoorCode extends BaseEntity {

    private String code; //二维码
    private String time; //时间
    private String state;//状态 0 成功 -1 失败
    /**
     * 详细信息
     */



    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public OpenDoorCode() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "OpenDoorCode{" +
                "code='" + code + '\'' +
                ", time='" + time + '\'' +
                ", state='" + state + '\'' +
                ", status=" + status +
                ", MSG='" + MSG + '\'' +
                '}';
    }
}
