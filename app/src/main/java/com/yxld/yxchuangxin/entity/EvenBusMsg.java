package com.yxld.yxchuangxin.entity;

/**
 * @author xlei
 * @Date 2018/5/26.
 *
 * evebus 发送消息的类 recode 发送到那个activity 区分
 */

public class EvenBusMsg {
    private String recode;
    private String result;
    private String msg;

    public String getRecode() {
        return recode;
    }

    public void setRecode(String recode) {
        this.recode = recode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public EvenBusMsg() {
    }

    public EvenBusMsg(String recode, String result, String msg) {

        this.recode = recode;
        this.result = result;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "EvenBusMsg{" + "recode='" + recode + '\'' + ", result='" + result + '\'' + ", msg='" + msg + '\'' + '}';
    }
}
