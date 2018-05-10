package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

/**
 * Created by William on 2017/12/22.
 */

public class RimDeleteOrderBean extends BaseEntity {

    /**
     * data : 003407sj1513906057290
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
