package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * @author xlei
 * @Date 2018/5/26.
 */

public class DoorInfo extends BaseEntity {
    private String code;
    private List<DoorInfoBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DoorInfoBean> getData() {
        return data;
    }

    public void setData(List<DoorInfoBean> data) {
        this.data = data;
    }

    public static class DoorInfoBean {

        /**
         * id : 51
         * name : 13区大门门禁
         * key : 442c05e69cc5
         * ip : 192.168.8.1
         * mac : 44:2c:05:e6:9c:c5
         * type : 0
         * danyuan_id : null
         * loudong_id : null
         * xiangmu_id : 443
         * gongsi_id : 23
         * lixian_mima : 111111
         * version : null
         * danyuan_name : null
         * loudong_name : null
         * xiangmu_name : 13区
         * tempPassword : 809794
         * json : null
         * online : false
         */

        private int id;
        private String name;
        private String key;
        private String ip;
        private String mac;
        private String type;
        private String danyuan_id;
        private String loudong_id;
        private int xiangmu_id;
        private int gongsi_id;
        private String lixian_mima;
        private Object version;
        private Object danyuan_name;
        private Object loudong_name;
        private String xiangmu_name;
        private String tempPassword;
        private Object json;
        private boolean online;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDanyuan_id() {
            return danyuan_id;
        }

        public void setDanyuan_id(String danyuan_id) {
            this.danyuan_id = danyuan_id;
        }

        public String getLoudong_id() {
            return loudong_id;
        }

        public void setLoudong_id(String loudong_id) {
            this.loudong_id = loudong_id;
        }

        public int getXiangmu_id() {
            return xiangmu_id;
        }

        public void setXiangmu_id(int xiangmu_id) {
            this.xiangmu_id = xiangmu_id;
        }

        public int getGongsi_id() {
            return gongsi_id;
        }

        public void setGongsi_id(int gongsi_id) {
            this.gongsi_id = gongsi_id;
        }

        public String getLixian_mima() {
            return lixian_mima;
        }

        public void setLixian_mima(String lixian_mima) {
            this.lixian_mima = lixian_mima;
        }

        public Object getVersion() {
            return version;
        }

        public void setVersion(Object version) {
            this.version = version;
        }

        public Object getDanyuan_name() {
            return danyuan_name;
        }

        public void setDanyuan_name(Object danyuan_name) {
            this.danyuan_name = danyuan_name;
        }

        public Object getLoudong_name() {
            return loudong_name;
        }

        public void setLoudong_name(Object loudong_name) {
            this.loudong_name = loudong_name;
        }

        public String getXiangmu_name() {
            return xiangmu_name;
        }

        public void setXiangmu_name(String xiangmu_name) {
            this.xiangmu_name = xiangmu_name;
        }

        public String getTempPassword() {
            return tempPassword;
        }

        public void setTempPassword(String tempPassword) {
            this.tempPassword = tempPassword;
        }

        public Object getJson() {
            return json;
        }

        public void setJson(Object json) {
            this.json = json;
        }

        public boolean isOnline() {
            return online;
        }

        public void setOnline(boolean online) {
            this.online = online;
        }
    }
}
