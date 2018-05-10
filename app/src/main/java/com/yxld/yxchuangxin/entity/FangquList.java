package com.yxld.yxchuangxin.entity;

import java.util.List;

/**
 * 作者：Android on 2017/9/7
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class FangquList {
    private  List<FangquEntity.DataBean> fangQuList;
    private String fangQuName;

    public List<FangquEntity.DataBean> getFangQuList() {
        return fangQuList;
    }

    public void setFangQuList(List<FangquEntity.DataBean> fangQuList) {
        this.fangQuList = fangQuList;
    }

    public String getFangQuName() {
        return fangQuName;
    }

    public void setFangQuName(String fangQuName) {
        this.fangQuName = fangQuName;
    }
}
