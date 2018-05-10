package com.yxld.yxchuangxin.entity;

/**
 * 作者：hu on 2017/7/12
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class PrePay {

    /**
     * data : 非固定车位cw1499859303383
     * success : 0
     * msg : ok
     */

    private String data;
    private int success;
    private String msg;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
