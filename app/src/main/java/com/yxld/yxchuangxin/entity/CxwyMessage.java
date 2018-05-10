package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：hu on 2017/6/26
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class CxwyMessage extends BaseEntity {

    private List<RowsBean> rows;

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable {
        /**
         * id : 203
         * tongzhiBiaoti : 门禁调试通知
         * tongzhiFabushijian : 2017-04-07
         * tongzhiJieshushijian : 2017-06-15
         * tongzhiLeixing : 1
         * tongzhiLuopan : 中远公馆
         * tongzhiLuopanid : 346
         * tongzhiNeirong : 尊敬的各位业主、租户：您们好！门禁设备硬件更新中，这期间为您带来不便，敬请谅解！湖南创欣物业有限公司2017年04月07日
         * tongzhiqiFaqiren : 湖南创欣物业
         * xnName :
         */

        private int id;
        private String tongzhiBiaoti;
        private String tongzhiFabushijian;
        private String tongzhiJieshushijian;
        private String tongzhiLeixing;
        private String tongzhiLuopan;
        private int tongzhiLuopanid;
        private String tongzhiNeirong;
        private String tongzhiqiFaqiren;
        private String xnName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTongzhiBiaoti() {
            return tongzhiBiaoti;
        }

        public void setTongzhiBiaoti(String tongzhiBiaoti) {
            this.tongzhiBiaoti = tongzhiBiaoti;
        }

        public String getTongzhiFabushijian() {
            return tongzhiFabushijian;
        }

        public void setTongzhiFabushijian(String tongzhiFabushijian) {
            this.tongzhiFabushijian = tongzhiFabushijian;
        }

        public String getTongzhiJieshushijian() {
            return tongzhiJieshushijian;
        }

        public void setTongzhiJieshushijian(String tongzhiJieshushijian) {
            this.tongzhiJieshushijian = tongzhiJieshushijian;
        }

        public String getTongzhiLeixing() {
            return tongzhiLeixing;
        }

        public void setTongzhiLeixing(String tongzhiLeixing) {
            this.tongzhiLeixing = tongzhiLeixing;
        }

        public String getTongzhiLuopan() {
            return tongzhiLuopan;
        }

        public void setTongzhiLuopan(String tongzhiLuopan) {
            this.tongzhiLuopan = tongzhiLuopan;
        }

        public int getTongzhiLuopanid() {
            return tongzhiLuopanid;
        }

        public void setTongzhiLuopanid(int tongzhiLuopanid) {
            this.tongzhiLuopanid = tongzhiLuopanid;
        }

        public String getTongzhiNeirong() {
            return tongzhiNeirong;
        }

        public void setTongzhiNeirong(String tongzhiNeirong) {
            this.tongzhiNeirong = tongzhiNeirong;
        }

        public String getTongzhiqiFaqiren() {
            return tongzhiqiFaqiren;
        }

        public void setTongzhiqiFaqiren(String tongzhiqiFaqiren) {
            this.tongzhiqiFaqiren = tongzhiqiFaqiren;
        }

        public String getXnName() {
            return xnName;
        }

        public void setXnName(String xnName) {
            this.xnName = xnName;
        }
    }
}
