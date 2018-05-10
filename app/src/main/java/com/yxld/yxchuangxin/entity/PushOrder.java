package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

/**
 * Created by hu on 2017/5/8.
 */

public class PushOrder extends BaseEntity {

    /**
     * data : 1494229969258
     * msg : 预约成功
     * success : 1
     * total : 0
     */

    private String data;
    private String success;
    private int total;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
