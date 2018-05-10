package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * Created by yishangfei on 2017/2/22 0022.
 * 邮箱：yishangfei@foxmail.com
 */
public class AppCameraList extends BaseEntity {

    private List<AppCameraList> data;

    public List<AppCameraList> getData() {
        return data;
    }

    public void setData(List<AppCameraList> data) {
        this.data = data;
    }

    /**
     * expired : 0
     * ipcstatus : 01
     * sb_id : 53
     * sb_ipc_id : 5969657
     * sb_ipc_pwd : 123
     * sb_jointime : {"date":2,"day":4,"hours":16,"minutes":51,"month":2,"nanos":0,"seconds":54,"time":1488444714000,"timezoneOffset":-480,"year":117}
     * sb_name : 测试
     * sb_zhanghao :
     */

    private String expired;
    private String ipcstatus;
    private int sb_id;
    private String sb_ipc_id;
    private String sb_ipc_pwd;
    private String sb_name;
    private String sb_zhanghao;
    private int sb_status;
    private int defenceState;

    public int getJiashu() {
        return jiashu;
    }

    public void setJiashu(int jiashu) {
        this.jiashu = jiashu;
    }

    private int jiashu;

    public int getDefenceState() {
        return defenceState;
    }

    public void setDefenceState(int defenceState) {
        this.defenceState = defenceState;
    }

    public int getSb_status() {
        return sb_status;
    }

    public void setSb_status(int sb_status) {
        this.sb_status = sb_status;
    }


    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public String getIpcstatus() {
        return ipcstatus;
    }

    public void setIpcstatus(String ipcstatus) {
        this.ipcstatus = ipcstatus;
    }

    public int getSb_id() {
        return sb_id;
    }

    public void setSb_id(int sb_id) {
        this.sb_id = sb_id;
    }

    public String getSb_ipc_id() {
        return sb_ipc_id;
    }

    public void setSb_ipc_id(String sb_ipc_id) {
        this.sb_ipc_id = sb_ipc_id;
    }

    public String getSb_ipc_pwd() {
        return sb_ipc_pwd;
    }

    public void setSb_ipc_pwd(String sb_ipc_pwd) {
        this.sb_ipc_pwd = sb_ipc_pwd;
    }


    public String getSb_name() {
        return sb_name;
    }

    public void setSb_name(String sb_name) {
        this.sb_name = sb_name;
    }

    public String getSb_zhanghao() {
        return sb_zhanghao;
    }

    public void setSb_zhanghao(String sb_zhanghao) {
        this.sb_zhanghao = sb_zhanghao;
    }
}
