package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

/**
 * Created by William on 2018/12/14.
 */

public class BaseBack3 extends BaseEntity {

    //    success true
//    status 0
//    msg 返回成功
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
