package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

/**
 * Created by William on 2018/12/26.
 */

public class QiniuTokenYwh extends BaseEntity {

    /**
     * success : true
     * code : 200
     * error : null
     * data : 4mJyuj6g6jjfYr2w1LBlrARdzscugcgVKAntzpfD:-z7epvkp_hlid_06S8iDZODibYA
     * =:eyJzY29wZSI6Im1vbml0b3JzdG9yYWdlIiwiZGVhZGxpbmUiOjE1NDU3OTM1NDZ9
     * rows : 4mJyuj6g6jjfYr2w1LBlrARdzscugcgVKAntzpfD:-z7epvkp_hlid_06S8iDZODibYA
     * =:eyJzY29wZSI6Im1vbml0b3JzdG9yYWdlIiwiZGVhZGxpbmUiOjE1NDU3OTM1NDZ9
     * total : null
     * token : null
     */

    private boolean success;
    private int code;
    private Object error;
    private String data;
    private String rows;
    private Object total;
    private Object token;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }
}
