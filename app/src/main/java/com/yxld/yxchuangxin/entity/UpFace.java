package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

/**
 * Created by William on 2018/12/14.
 */

public class UpFace extends BaseEntity{

    /**
     * code : -1
     * success : false
     * error : 后台数据为空
     */

    private int code;
    private boolean success;
    private String error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
