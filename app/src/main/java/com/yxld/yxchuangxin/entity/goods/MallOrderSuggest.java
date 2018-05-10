package com.yxld.yxchuangxin.entity.goods;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * @author xlei
 * @Date 2017/10/30.
 */

public class MallOrderSuggest extends BaseEntity {
    private int total;
    private List<DataBean> data;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class DataBean {
        private int id;
        private String tsjyChuliJieguo;
        private String tsjyChuliShijian;
        private int tsjyChulirenId;
        private String tsjyChulirenMing;
        private String tsjyDindanBianhao;
        private int tsjyGongsiId;
        private int tsjyLeixing;
        private String tsjyNeirong;
        private String tsjyTijiaoShijian;
        private int tsjyXiangmuId;
        private int tsjyYezhuId;
        private String tsjyYezhuMing;
        private int tsjyZhuangtai;
        private String xiangmuName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTsjyChuliJieguo() {
            return tsjyChuliJieguo;
        }

        public void setTsjyChuliJieguo(String tsjyChuliJieguo) {
            this.tsjyChuliJieguo = tsjyChuliJieguo;
        }

        public String getTsjyChuliShijian() {
            return tsjyChuliShijian;
        }

        public void setTsjyChuliShijian(String tsjyChuliShijian) {
            this.tsjyChuliShijian = tsjyChuliShijian;
        }

        public int getTsjyChulirenId() {
            return tsjyChulirenId;
        }

        public void setTsjyChulirenId(int tsjyChulirenId) {
            this.tsjyChulirenId = tsjyChulirenId;
        }

        public String getTsjyChulirenMing() {
            return tsjyChulirenMing;
        }

        public void setTsjyChulirenMing(String tsjyChulirenMing) {
            this.tsjyChulirenMing = tsjyChulirenMing;
        }

        public String getTsjyDindanBianhao() {
            return tsjyDindanBianhao;
        }

        public void setTsjyDindanBianhao(String tsjyDindanBianhao) {
            this.tsjyDindanBianhao = tsjyDindanBianhao;
        }

        public int getTsjyGongsiId() {
            return tsjyGongsiId;
        }

        public void setTsjyGongsiId(int tsjyGongsiId) {
            this.tsjyGongsiId = tsjyGongsiId;
        }

        public int getTsjyLeixing() {
            return tsjyLeixing;
        }

        public void setTsjyLeixing(int tsjyLeixing) {
            this.tsjyLeixing = tsjyLeixing;
        }

        public String getTsjyNeirong() {
            return tsjyNeirong;
        }

        public void setTsjyNeirong(String tsjyNeirong) {
            this.tsjyNeirong = tsjyNeirong;
        }

        public String getTsjyTijiaoShijian() {
            return tsjyTijiaoShijian;
        }

        public void setTsjyTijiaoShijian(String tsjyTijiaoShijian) {
            this.tsjyTijiaoShijian = tsjyTijiaoShijian;
        }

        public int getTsjyXiangmuId() {
            return tsjyXiangmuId;
        }

        public void setTsjyXiangmuId(int tsjyXiangmuId) {
            this.tsjyXiangmuId = tsjyXiangmuId;
        }

        public int getTsjyYezhuId() {
            return tsjyYezhuId;
        }

        public void setTsjyYezhuId(int tsjyYezhuId) {
            this.tsjyYezhuId = tsjyYezhuId;
        }

        public String getTsjyYezhuMing() {
            return tsjyYezhuMing;
        }

        public void setTsjyYezhuMing(String tsjyYezhuMing) {
            this.tsjyYezhuMing = tsjyYezhuMing;
        }

        public int getTsjyZhuangtai() {
            return tsjyZhuangtai;
        }

        public void setTsjyZhuangtai(int tsjyZhuangtai) {
            this.tsjyZhuangtai = tsjyZhuangtai;
        }

        public String getXiangmuName() {
            return xiangmuName;
        }

        public void setXiangmuName(String xiangmuName) {
            this.xiangmuName = xiangmuName;
        }
    }

    public static class RowsBean {
        private int id;
        private String tsjyChuliJieguo;
        private String tsjyChuliShijian;
        private int tsjyChulirenId;
        private String tsjyChulirenMing;
        private String tsjyDindanBianhao;
        private int tsjyGongsiId;
        private int tsjyLeixing;
        private String tsjyNeirong;
        private String tsjyTijiaoShijian;
        private int tsjyXiangmuId;
        private int tsjyYezhuId;
        private String tsjyYezhuMing;
        private int tsjyZhuangtai;
        private String xiangmuName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTsjyChuliJieguo() {
            return tsjyChuliJieguo;
        }

        public void setTsjyChuliJieguo(String tsjyChuliJieguo) {
            this.tsjyChuliJieguo = tsjyChuliJieguo;
        }

        public String getTsjyChuliShijian() {
            return tsjyChuliShijian;
        }

        public void setTsjyChuliShijian(String tsjyChuliShijian) {
            this.tsjyChuliShijian = tsjyChuliShijian;
        }

        public int getTsjyChulirenId() {
            return tsjyChulirenId;
        }

        public void setTsjyChulirenId(int tsjyChulirenId) {
            this.tsjyChulirenId = tsjyChulirenId;
        }

        public String getTsjyChulirenMing() {
            return tsjyChulirenMing;
        }

        public void setTsjyChulirenMing(String tsjyChulirenMing) {
            this.tsjyChulirenMing = tsjyChulirenMing;
        }

        public String getTsjyDindanBianhao() {
            return tsjyDindanBianhao;
        }

        public void setTsjyDindanBianhao(String tsjyDindanBianhao) {
            this.tsjyDindanBianhao = tsjyDindanBianhao;
        }

        public int getTsjyGongsiId() {
            return tsjyGongsiId;
        }

        public void setTsjyGongsiId(int tsjyGongsiId) {
            this.tsjyGongsiId = tsjyGongsiId;
        }

        public int getTsjyLeixing() {
            return tsjyLeixing;
        }

        public void setTsjyLeixing(int tsjyLeixing) {
            this.tsjyLeixing = tsjyLeixing;
        }

        public String getTsjyNeirong() {
            return tsjyNeirong;
        }

        public void setTsjyNeirong(String tsjyNeirong) {
            this.tsjyNeirong = tsjyNeirong;
        }

        public String getTsjyTijiaoShijian() {
            return tsjyTijiaoShijian;
        }

        public void setTsjyTijiaoShijian(String tsjyTijiaoShijian) {
            this.tsjyTijiaoShijian = tsjyTijiaoShijian;
        }

        public int getTsjyXiangmuId() {
            return tsjyXiangmuId;
        }

        public void setTsjyXiangmuId(int tsjyXiangmuId) {
            this.tsjyXiangmuId = tsjyXiangmuId;
        }

        public int getTsjyYezhuId() {
            return tsjyYezhuId;
        }

        public void setTsjyYezhuId(int tsjyYezhuId) {
            this.tsjyYezhuId = tsjyYezhuId;
        }

        public String getTsjyYezhuMing() {
            return tsjyYezhuMing;
        }

        public void setTsjyYezhuMing(String tsjyYezhuMing) {
            this.tsjyYezhuMing = tsjyYezhuMing;
        }

        public int getTsjyZhuangtai() {
            return tsjyZhuangtai;
        }

        public void setTsjyZhuangtai(int tsjyZhuangtai) {
            this.tsjyZhuangtai = tsjyZhuangtai;
        }

        public String getXiangmuName() {
            return xiangmuName;
        }

        public void setXiangmuName(String xiangmuName) {
            this.xiangmuName = xiangmuName;
        }

        @Override
        public String toString() {
            return "RowsBean{" +
                    "id=" + id +
                    ", tsjyChuliJieguo='" + tsjyChuliJieguo + '\'' +
                    ", tsjyChuliShijian='" + tsjyChuliShijian + '\'' +
                    ", tsjyChulirenId=" + tsjyChulirenId +
                    ", tsjyChulirenMing='" + tsjyChulirenMing + '\'' +
                    ", tsjyDindanBianhao='" + tsjyDindanBianhao + '\'' +
                    ", tsjyGongsiId=" + tsjyGongsiId +
                    ", tsjyLeixing=" + tsjyLeixing +
                    ", tsjyNeirong='" + tsjyNeirong + '\'' +
                    ", tsjyTijiaoShijian='" + tsjyTijiaoShijian + '\'' +
                    ", tsjyXiangmuId=" + tsjyXiangmuId +
                    ", tsjyYezhuId=" + tsjyYezhuId +
                    ", tsjyYezhuMing='" + tsjyYezhuMing + '\'' +
                    ", tsjyZhuangtai=" + tsjyZhuangtai +
                    ", xiangmuName='" + xiangmuName + '\'' +
                    '}';
        }
    }
}
