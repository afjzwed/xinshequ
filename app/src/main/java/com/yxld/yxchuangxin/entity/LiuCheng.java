package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * @author xlei
 * @Date 2018/5/14.
 */

public class LiuCheng extends BaseEntity {


    /**
     * baoxiuId : 711
     * zhuangtai : 3
     * time : null
     * name : null
     * zhuangtaiName : 查看现场中（维修人，负责人）
     */

    private int baoxiuId;
    private int zhuangtai;
    private String time;
    private String name;
    private String zhuangtaiName;
    private List<LiuCheng> rows;

    public List<LiuCheng> getRows() {
        return rows;
    }

    public void setRows(List<LiuCheng> rows) {
        this.rows = rows;
    }

    public int getBaoxiuId() {
        return baoxiuId;
    }

    public void setBaoxiuId(int baoxiuId) {
        this.baoxiuId = baoxiuId;
    }

    public int getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(int zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZhuangtaiName() {
        return zhuangtaiName;
    }

    public void setZhuangtaiName(String zhuangtaiName) {
        this.zhuangtaiName = zhuangtaiName;
    }
}
