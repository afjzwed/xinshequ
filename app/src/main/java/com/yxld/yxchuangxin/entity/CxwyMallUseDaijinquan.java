package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * @ClassName: CxwyMallUseDaijinquan 
 * @Description: 代金券使用记录实体 
 * @author wwx
 * @date 2016-8-1 下午04:29:11 
 */
public class CxwyMallUseDaijinquan extends BaseEntity implements java.io.Serializable {


	private List<CxwyMallUseDaijinquan> rows;

    public List<CxwyMallUseDaijinquan> getRows() {
        return rows;
    }

    public void setRows(List<CxwyMallUseDaijinquan> rows) {
        this.rows = rows;
    }

    /**主键 代金券使用记录id*/
     private Integer daijinquanUseId;
     /** 代金券id*/   
     private Integer daijinquanUseDjqid;
    /** 代金券用户id*/
    private Integer daijinquanUseYezhuid;
     /** 代金券用户手机号码*/   
     private String daijinquanUseShoujihao;
     /** 代金券类型：注册红包，充值红包,活动红包,其他红包等*/   
     private String daijinquanUseType;
     /** 代金券金额*/
     private String daijinquanUseJine;
     /** 使用起始价格 例如：满20元可用 */
     private String daijinquanUseShiyongjia;
     /** 代金券使用开始时间*/
     private String daijinquanUseStarttime;
     /** 代金券使用结束时间*/
     private String daijinquanUseEndtime;
     /** 代金券使用状态  0待使用 ，1已使用，2已过期 */
     private Integer daijinquanUseState;
     /** 代金券领取时间*/
     private String daijinquanUseFaxingtime;
     /** 代金券发行楼盘*/
     private String daijinquanUseFaxingloupan;
     /** 代金券备用1*/
     private String daijinquanUseBeiyong1;
     /** 代金券备用1*/
     private String daijinquanUseBeiyong2;


    // Constructors

    /** default constructor */
    public CxwyMallUseDaijinquan() {
    }

    
    /** full constructor */
    public CxwyMallUseDaijinquan(Integer daijinquanUseDjqid, Integer daijinquanUseYezhuid, String daijinquanUseShoujihao, String daijinquanUseType, String daijinquanUseJine, String daijinquanUseShiyongjia, String daijinquanUseStarttime, String daijinquanUseEndtime, Integer daijinquanUseState, String daijinquanUseFaxingtime, String daijinquanUseFaxingloupan, String daijinquanUseBeiyong1, String daijinquanUseBeiyong2) {
        this.daijinquanUseDjqid = daijinquanUseDjqid;
        this.daijinquanUseYezhuid = daijinquanUseYezhuid;
        this.daijinquanUseShoujihao = daijinquanUseShoujihao;
        this.daijinquanUseType = daijinquanUseType;
        this.daijinquanUseJine = daijinquanUseJine;
        this.daijinquanUseShiyongjia = daijinquanUseShiyongjia;
        this.daijinquanUseStarttime = daijinquanUseStarttime;
        this.daijinquanUseEndtime = daijinquanUseEndtime;
        this.daijinquanUseState = daijinquanUseState;
        this.daijinquanUseFaxingtime = daijinquanUseFaxingtime;
        this.daijinquanUseFaxingloupan = daijinquanUseFaxingloupan;
        this.daijinquanUseBeiyong1 = daijinquanUseBeiyong1;
        this.daijinquanUseBeiyong2 = daijinquanUseBeiyong2;
    }

   
    // Property accessors

    public Integer getDaijinquanUseId() {
        return this.daijinquanUseId;
    }
    
    public void setDaijinquanUseId(Integer daijinquanUseId) {
        this.daijinquanUseId = daijinquanUseId;
    }

    public Integer getDaijinquanUseDjqid() {
        return this.daijinquanUseDjqid;
    }
    
    public void setDaijinquanUseDjqid(Integer daijinquanUseDjqid) {
        this.daijinquanUseDjqid = daijinquanUseDjqid;
    }

    public Integer getDaijinquanUseYezhuid() {
        return daijinquanUseYezhuid;
    }

    public void setDaijinquanUseYezhuid(Integer daijinquanUseYezhuid) {
        this.daijinquanUseYezhuid = daijinquanUseYezhuid;
    }

    public String getDaijinquanUseShoujihao() {
        return this.daijinquanUseShoujihao;
    }
    
    public void setDaijinquanUseShoujihao(String daijinquanUseShoujihao) {
        this.daijinquanUseShoujihao = daijinquanUseShoujihao;
    }

    public String getDaijinquanUseType() {
        return this.daijinquanUseType;
    }
    
    public void setDaijinquanUseType(String daijinquanUseType) {
        this.daijinquanUseType = daijinquanUseType;
    }

    public String getDaijinquanUseJine() {
        return this.daijinquanUseJine;
    }
    
    public void setDaijinquanUseJine(String daijinquanUseJine) {
        this.daijinquanUseJine = daijinquanUseJine;
    }

    public String getDaijinquanUseShiyongjia() {
        return this.daijinquanUseShiyongjia;
    }
    
    public void setDaijinquanUseShiyongjia(String daijinquanUseShiyongjia) {
        this.daijinquanUseShiyongjia = daijinquanUseShiyongjia;
    }

    public String getDaijinquanUseStarttime() {
        return this.daijinquanUseStarttime;
    }
    
    public void setDaijinquanUseStarttime(String daijinquanUseStarttime) {
        this.daijinquanUseStarttime = daijinquanUseStarttime;
    }

    public String getDaijinquanUseEndtime() {
        return this.daijinquanUseEndtime;
    }
    
    public void setDaijinquanUseEndtime(String daijinquanUseEndtime) {
        this.daijinquanUseEndtime = daijinquanUseEndtime;
    }

    public Integer getDaijinquanUseState() {
        return this.daijinquanUseState;
    }
    
    public void setDaijinquanUseState(Integer daijinquanUseState) {
        this.daijinquanUseState = daijinquanUseState;
    }

    public String getDaijinquanUseFaxingtime() {
        return this.daijinquanUseFaxingtime;
    }
    
    public void setDaijinquanUseFaxingtime(String daijinquanUseFaxingtime) {
        this.daijinquanUseFaxingtime = daijinquanUseFaxingtime;
    }

    public String getDaijinquanUseFaxingloupan() {
        return this.daijinquanUseFaxingloupan;
    }
    
    public void setDaijinquanUseFaxingloupan(String daijinquanUseFaxingloupan) {
        this.daijinquanUseFaxingloupan = daijinquanUseFaxingloupan;
    }

    public String getDaijinquanUseBeiyong1() {
        return this.daijinquanUseBeiyong1;
    }
    
    public void setDaijinquanUseBeiyong1(String daijinquanUseBeiyong1) {
        this.daijinquanUseBeiyong1 = daijinquanUseBeiyong1;
    }

    public String getDaijinquanUseBeiyong2() {
        return this.daijinquanUseBeiyong2;
    }
    
    public void setDaijinquanUseBeiyong2(String daijinquanUseBeiyong2) {
        this.daijinquanUseBeiyong2 = daijinquanUseBeiyong2;
    }

    @Override
    public String toString() {
        return "CxwyMallUseDaijinquan{" +
                "daijinquanUseId=" + daijinquanUseId +
                ", daijinquanUseDjqid=" + daijinquanUseDjqid +
                ", daijinquanUseYezhuid=" + daijinquanUseYezhuid +
                ", daijinquanUseShoujihao='" + daijinquanUseShoujihao + '\'' +
                ", daijinquanUseType='" + daijinquanUseType + '\'' +
                ", daijinquanUseJine='" + daijinquanUseJine + '\'' +
                ", daijinquanUseShiyongjia='" + daijinquanUseShiyongjia + '\'' +
                ", daijinquanUseStarttime='" + daijinquanUseStarttime + '\'' +
                ", daijinquanUseEndtime='" + daijinquanUseEndtime + '\'' +
                ", daijinquanUseState=" + daijinquanUseState +
                ", daijinquanUseFaxingtime='" + daijinquanUseFaxingtime + '\'' +
                ", daijinquanUseFaxingloupan='" + daijinquanUseFaxingloupan + '\'' +
                ", daijinquanUseBeiyong1='" + daijinquanUseBeiyong1 + '\'' +
                ", daijinquanUseBeiyong2='" + daijinquanUseBeiyong2 + '\'' +
                '}';
    }
}