package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

/**
 * 作者：Android on 2017/10/27
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class IsNight extends BaseEntity {

    /**
     * success : true
     * rows : 日间
     * total : null
     */

    private boolean success;
    private String rows;
    private Object total;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }
}
