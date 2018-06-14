package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * @author xlei
 * @Date 2018/6/11.
 */

public class MenJinShareMemberBean extends BaseEntity {


    private List<MenJinShareMemberBean> data;

    public List<MenJinShareMemberBean> getData() {
        return data;
    }

    public void setData(List<MenJinShareMemberBean> data) {
        this.data = data;
    }

    /**
     * yezhuDianhua : 15243648097
     * gongsiId : 23
     * fanghao : 6982
     * xiangmuId : 443
     * yezhuId : 8611
     * danyuanId : 13
     * dianhuas : null
     * id : 21
     * loudongId : 5
     * yezhuIds : null
     */

    private String yezhuDianhua;
    private int gongsiId;
    private String fanghao;
    private int xiangmuId;
    private int yezhuId;
    private int danyuanId;
    private Object dianhuas;
    private int id;
    private int loudongId;
    private Object yezhuIds;

    public String getYezhuDianhua() {
        return yezhuDianhua;
    }

    public void setYezhuDianhua(String yezhuDianhua) {
        this.yezhuDianhua = yezhuDianhua;
    }

    public int getGongsiId() {
        return gongsiId;
    }

    public void setGongsiId(int gongsiId) {
        this.gongsiId = gongsiId;
    }

    public String getFanghao() {
        return fanghao;
    }

    public void setFanghao(String fanghao) {
        this.fanghao = fanghao;
    }

    public int getXiangmuId() {
        return xiangmuId;
    }

    public void setXiangmuId(int xiangmuId) {
        this.xiangmuId = xiangmuId;
    }

    public int getYezhuId() {
        return yezhuId;
    }

    public void setYezhuId(int yezhuId) {
        this.yezhuId = yezhuId;
    }

    public int getDanyuanId() {
        return danyuanId;
    }

    public void setDanyuanId(int danyuanId) {
        this.danyuanId = danyuanId;
    }

    public Object getDianhuas() {
        return dianhuas;
    }

    public void setDianhuas(Object dianhuas) {
        this.dianhuas = dianhuas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoudongId() {
        return loudongId;
    }

    public void setLoudongId(int loudongId) {
        this.loudongId = loudongId;
    }

    public Object getYezhuIds() {
        return yezhuIds;
    }

    public void setYezhuIds(Object yezhuIds) {
        this.yezhuIds = yezhuIds;
    }
}
