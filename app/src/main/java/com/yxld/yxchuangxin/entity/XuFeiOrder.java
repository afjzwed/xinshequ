package com.yxld.yxchuangxin.entity;

/**
 * @author Yuan.Y.Q
 * @Date 2017/10/10.
 */

public class XuFeiOrder {
    private String msg;
    private String data;
    private boolean success;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "XuFeiOrder{" +
                "msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                ", success=" + success +
                ", status=" + status +
                '}';
    }
}
