package com.yxld.yxchuangxin.entity.goods;

import com.yxld.yxchuangxin.base.BaseEntity;

/**
 * @author xlei
 * @Date 2017/11/6.
 */

public class BaseEntityAll extends BaseEntity {
    private String data;
    private boolean success;
    private int total;
    private String rows;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }
}
