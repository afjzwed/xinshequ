package com.yxld.yxchuangxin.entity;

/**
 * 作者：hu on 2017/7/6
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class LockCar {

    /**
     * msg : 车辆不在场
     * success : false
     * data : -1
     */

    private String msg;
    private boolean success;
    private int data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
