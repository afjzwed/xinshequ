package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * CxwyBaoxiu entity. @author MyEclipse Persistence Tools
 */

public class CxwyBaoxiu extends BaseEntity{

    /**
     * rows : [{"baoxiu_bumen":"","baoxiu_bumenid":0,"baoxiu_cailiaofei":0,"baoxiu_cailiaoid":"","baoxiu_chayanimg":"","baoxiu_danhao":"536343842445565","baoxiu_danyuan":"1","baoxiu_dianhua":"18670819116","baoxiu_didian":"哈哈","baoxiu_fanghao":"301","baoxiu_feiyongheji":0,"baoxiu_fuzerenyijian":"","baoxiu_fuzerenyijiantime":"","baoxiu_fw_goutong":"","baoxiu_fw_jishixing":"","baoxiu_fw_limao":"","baoxiu_fw_liucheng":"","baoxiu_fw_qingjie":"","baoxiu_huifangtime":"","baoxiu_huifangtype":"","baoxiu_huifangyijian":"","baoxiu_id":175,"baoxiu_img":"upload/baoxiu/img/2017-06/android_1498554394284;upload/baoxiu/img/2017-06/android_1498554394293;upload/baoxiu/img/2017-06/android_1498554394295;","baoxiu_leixing":"业主/客户","baoxiu_loudong":"1","baoxiu_loupan":"中远公馆","baoxiu_loupanid":346,"baoxiu_lrtime":"2017-06-27 17:07:32","baoxiu_name":"胡智鹏","baoxiu_paidanren":"","baoxiu_paidantime":"","baoxiu_qitafei":0,"baoxiu_rengongfei":0,"baoxiu_shuijinfei":0,"baoxiu_status":"1","baoxiu_weiwaidanweiimg":"","baoxiu_weixiudanwei":"","baoxiu_weixiupaigongdan":"","baoxiu_xiangmu":"呵呵","baoxiu_xingzhi":"3","baoxiu_zx_bumen":"","baoxiu_zx_bumenid":0,"baoxiu_zx_fuzeren":"","baoxiu_zx_fuzerenid":0,"baoxiu_zx_weixiuren":"","baoxiu_zx_weixiurenid":0,"baoxiu_zx_weixiurenphone":""},{"baoxiu_bumen":"","baoxiu_bumenid":0,"baoxiu_cailiaofei":0,"baoxiu_cailiaoid":"","baoxiu_chayanimg":"","baoxiu_danhao":"535956402057647","baoxiu_danyuan":"1","baoxiu_dianhua":"18670819116","baoxiu_didian":"哈哈","baoxiu_fanghao":"301","baoxiu_feiyongheji":0,"baoxiu_fuzerenyijian":"","baoxiu_fuzerenyijiantime":"","baoxiu_fw_goutong":"","baoxiu_fw_jishixing":"","baoxiu_fw_limao":"","baoxiu_fw_liucheng":"","baoxiu_fw_qingjie":"","baoxiu_huifangtime":"","baoxiu_huifangtype":"","baoxiu_huifangyijian":"","baoxiu_id":174,"baoxiu_img":"upload/baoxiu/img/2017-06/android_1498554003932;upload/baoxiu/img/2017-06/android_1498554003934;upload/baoxiu/img/2017-06/android_1498554003926;","baoxiu_leixing":"业主/客户","baoxiu_loudong":"1","baoxiu_loupan":"中远公馆","baoxiu_loupanid":346,"baoxiu_lrtime":"2017-06-27 17:01:05","baoxiu_name":"胡智鹏","baoxiu_paidanren":"","baoxiu_paidantime":"","baoxiu_qitafei":0,"baoxiu_rengongfei":0,"baoxiu_shuijinfei":0,"baoxiu_status":"1","baoxiu_weiwaidanweiimg":"","baoxiu_weixiudanwei":"","baoxiu_weixiupaigongdan":"","baoxiu_xiangmu":"呵呵","baoxiu_xingzhi":"3","baoxiu_zx_bumen":"","baoxiu_zx_bumenid":0,"baoxiu_zx_fuzeren":"","baoxiu_zx_fuzerenid":0,"baoxiu_zx_weixiuren":"","baoxiu_zx_weixiurenid":0,"baoxiu_zx_weixiurenphone":""}]
     * status : 0
     * total : 2
     */

    private String statusX;
    private int total;
    private List<RowsBean> rows;

    public String getStatusX() {
        return statusX;
    }

    public void setStatusX(String statusX) {
        this.statusX = statusX;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable{
        /**
         * baoxiu_bumen :
         * baoxiu_bumenid : 0
         * baoxiu_cailiaofei : 0
         * baoxiu_cailiaoid :
         * baoxiu_chayanimg :
         * baoxiu_danhao : 536343842445565
         * baoxiu_danyuan : 1
         * baoxiu_dianhua : 18670819116
         * baoxiu_didian : 哈哈
         * baoxiu_fanghao : 301
         * baoxiu_feiyongheji : 0
         * baoxiu_fuzerenyijian :
         * baoxiu_fuzerenyijiantime :
         * baoxiu_fw_goutong :
         * baoxiu_fw_jishixing :
         * baoxiu_fw_limao :
         * baoxiu_fw_liucheng :
         * baoxiu_fw_qingjie :
         * baoxiu_huifangtime :
         * baoxiu_huifangtype :
         * baoxiu_huifangyijian :
         * baoxiu_id : 175
         * baoxiu_img : upload/baoxiu/img/2017-06/android_1498554394284;upload/baoxiu/img/2017-06/android_1498554394293;upload/baoxiu/img/2017-06/android_1498554394295;
         * baoxiu_leixing : 业主/客户
         * baoxiu_loudong : 1
         * baoxiu_loupan : 中远公馆
         * baoxiu_loupanid : 346
         * baoxiu_lrtime : 2017-06-27 17:07:32
         * baoxiu_name : 胡智鹏
         * baoxiu_paidanren :
         * baoxiu_paidantime :
         * baoxiu_qitafei : 0
         * baoxiu_rengongfei : 0
         * baoxiu_shuijinfei : 0
         * baoxiu_status : 1
         * baoxiu_weiwaidanweiimg :
         * baoxiu_weixiudanwei :
         * baoxiu_weixiupaigongdan :
         * baoxiu_xiangmu : 呵呵
         * baoxiu_xingzhi : 3
         * baoxiu_zx_bumen :
         * baoxiu_zx_bumenid : 0
         * baoxiu_zx_fuzeren :
         * baoxiu_zx_fuzerenid : 0
         * baoxiu_zx_weixiuren :
         * baoxiu_zx_weixiurenid : 0
         * baoxiu_zx_weixiurenphone :
         */

        private String baoxiu_bumen;
        private int baoxiu_bumenid;
        private double baoxiu_cailiaofei;
        private String baoxiu_cailiaoid;
        private String baoxiu_chayanimg;
        private String baoxiu_danhao;
        private String baoxiu_danyuan;
        private String baoxiu_dianhua;
        private String baoxiu_didian;
        private String baoxiu_fanghao;
        private double baoxiu_feiyongheji;
        private String baoxiu_fuzerenyijian;
        private String baoxiu_fuzerenyijiantime;
        private String baoxiu_fw_goutong;
        private String baoxiu_fw_jishixing;
        private String baoxiu_fw_limao;
        private String baoxiu_fw_liucheng;
        private String baoxiu_fw_qingjie;
        private String baoxiu_huifangtime;
        private String baoxiu_huifangtype;
        private String baoxiu_huifangyijian;
        private int baoxiu_id;
        private String baoxiu_img;
        private String baoxiu_leixing;
        private String baoxiu_loudong;
        private String baoxiu_loupan;
        private int baoxiu_loupanid;
        private String baoxiu_lrtime;
        private String baoxiu_name;
        private String baoxiu_paidanren;
        private String baoxiu_paidantime;
        private double baoxiu_qitafei;
        private double baoxiu_rengongfei;
        private double baoxiu_shuijinfei;
        private String baoxiu_status;
        private String baoxiu_weiwaidanweiimg;
        private String baoxiu_weixiudanwei;
        private String baoxiu_weixiupaigongdan;
        private String baoxiu_xiangmu;
        private String baoxiu_xingzhi;
        private String baoxiu_zx_bumen;
        private int baoxiu_zx_bumenid;
        private String baoxiu_zx_fuzeren;
        private int baoxiu_zx_fuzerenid;
        private String baoxiu_zx_weixiuren;
        private int baoxiu_zx_weixiurenid;
        private String baoxiu_zx_weixiurenphone;

        public String getBaoxiu_bumen() {
            return baoxiu_bumen;
        }

        public void setBaoxiu_bumen(String baoxiu_bumen) {
            this.baoxiu_bumen = baoxiu_bumen;
        }

        public int getBaoxiu_bumenid() {
            return baoxiu_bumenid;
        }

        public void setBaoxiu_bumenid(int baoxiu_bumenid) {
            this.baoxiu_bumenid = baoxiu_bumenid;
        }

        public double getBaoxiu_cailiaofei() {
            return baoxiu_cailiaofei;
        }

        public void setBaoxiu_cailiaofei(double baoxiu_cailiaofei) {
            this.baoxiu_cailiaofei = baoxiu_cailiaofei;
        }

        public String getBaoxiu_cailiaoid() {
            return baoxiu_cailiaoid;
        }

        public void setBaoxiu_cailiaoid(String baoxiu_cailiaoid) {
            this.baoxiu_cailiaoid = baoxiu_cailiaoid;
        }

        public String getBaoxiu_chayanimg() {
            return baoxiu_chayanimg;
        }

        public void setBaoxiu_chayanimg(String baoxiu_chayanimg) {
            this.baoxiu_chayanimg = baoxiu_chayanimg;
        }

        public String getBaoxiu_danhao() {
            return baoxiu_danhao;
        }

        public void setBaoxiu_danhao(String baoxiu_danhao) {
            this.baoxiu_danhao = baoxiu_danhao;
        }

        public String getBaoxiu_danyuan() {
            return baoxiu_danyuan;
        }

        public void setBaoxiu_danyuan(String baoxiu_danyuan) {
            this.baoxiu_danyuan = baoxiu_danyuan;
        }

        public String getBaoxiu_dianhua() {
            return baoxiu_dianhua;
        }

        public void setBaoxiu_dianhua(String baoxiu_dianhua) {
            this.baoxiu_dianhua = baoxiu_dianhua;
        }

        public String getBaoxiu_didian() {
            return baoxiu_didian;
        }

        public void setBaoxiu_didian(String baoxiu_didian) {
            this.baoxiu_didian = baoxiu_didian;
        }

        public String getBaoxiu_fanghao() {
            return baoxiu_fanghao;
        }

        public void setBaoxiu_fanghao(String baoxiu_fanghao) {
            this.baoxiu_fanghao = baoxiu_fanghao;
        }

        public double getBaoxiu_feiyongheji() {
            return baoxiu_feiyongheji;
        }

        public void setBaoxiu_feiyongheji(double baoxiu_feiyongheji) {
            this.baoxiu_feiyongheji = baoxiu_feiyongheji;
        }

        public String getBaoxiu_fuzerenyijian() {
            return baoxiu_fuzerenyijian;
        }

        public void setBaoxiu_fuzerenyijian(String baoxiu_fuzerenyijian) {
            this.baoxiu_fuzerenyijian = baoxiu_fuzerenyijian;
        }

        public String getBaoxiu_fuzerenyijiantime() {
            return baoxiu_fuzerenyijiantime;
        }

        public void setBaoxiu_fuzerenyijiantime(String baoxiu_fuzerenyijiantime) {
            this.baoxiu_fuzerenyijiantime = baoxiu_fuzerenyijiantime;
        }

        public String getBaoxiu_fw_goutong() {
            return baoxiu_fw_goutong;
        }

        public void setBaoxiu_fw_goutong(String baoxiu_fw_goutong) {
            this.baoxiu_fw_goutong = baoxiu_fw_goutong;
        }

        public String getBaoxiu_fw_jishixing() {
            return baoxiu_fw_jishixing;
        }

        public void setBaoxiu_fw_jishixing(String baoxiu_fw_jishixing) {
            this.baoxiu_fw_jishixing = baoxiu_fw_jishixing;
        }

        public String getBaoxiu_fw_limao() {
            return baoxiu_fw_limao;
        }

        public void setBaoxiu_fw_limao(String baoxiu_fw_limao) {
            this.baoxiu_fw_limao = baoxiu_fw_limao;
        }

        public String getBaoxiu_fw_liucheng() {
            return baoxiu_fw_liucheng;
        }

        public void setBaoxiu_fw_liucheng(String baoxiu_fw_liucheng) {
            this.baoxiu_fw_liucheng = baoxiu_fw_liucheng;
        }

        public String getBaoxiu_fw_qingjie() {
            return baoxiu_fw_qingjie;
        }

        public void setBaoxiu_fw_qingjie(String baoxiu_fw_qingjie) {
            this.baoxiu_fw_qingjie = baoxiu_fw_qingjie;
        }

        public String getBaoxiu_huifangtime() {
            return baoxiu_huifangtime;
        }

        public void setBaoxiu_huifangtime(String baoxiu_huifangtime) {
            this.baoxiu_huifangtime = baoxiu_huifangtime;
        }

        public String getBaoxiu_huifangtype() {
            return baoxiu_huifangtype;
        }

        public void setBaoxiu_huifangtype(String baoxiu_huifangtype) {
            this.baoxiu_huifangtype = baoxiu_huifangtype;
        }

        public String getBaoxiu_huifangyijian() {
            return baoxiu_huifangyijian;
        }

        public void setBaoxiu_huifangyijian(String baoxiu_huifangyijian) {
            this.baoxiu_huifangyijian = baoxiu_huifangyijian;
        }

        public int getBaoxiu_id() {
            return baoxiu_id;
        }

        public void setBaoxiu_id(int baoxiu_id) {
            this.baoxiu_id = baoxiu_id;
        }

        public String getBaoxiu_img() {
            return baoxiu_img;
        }

        public void setBaoxiu_img(String baoxiu_img) {
            this.baoxiu_img = baoxiu_img;
        }

        public String getBaoxiu_leixing() {
            return baoxiu_leixing;
        }

        public void setBaoxiu_leixing(String baoxiu_leixing) {
            this.baoxiu_leixing = baoxiu_leixing;
        }

        public String getBaoxiu_loudong() {
            return baoxiu_loudong;
        }

        public void setBaoxiu_loudong(String baoxiu_loudong) {
            this.baoxiu_loudong = baoxiu_loudong;
        }

        public String getBaoxiu_loupan() {
            return baoxiu_loupan;
        }

        public void setBaoxiu_loupan(String baoxiu_loupan) {
            this.baoxiu_loupan = baoxiu_loupan;
        }

        public int getBaoxiu_loupanid() {
            return baoxiu_loupanid;
        }

        public void setBaoxiu_loupanid(int baoxiu_loupanid) {
            this.baoxiu_loupanid = baoxiu_loupanid;
        }

        public String getBaoxiu_lrtime() {
            return baoxiu_lrtime;
        }

        public void setBaoxiu_lrtime(String baoxiu_lrtime) {
            this.baoxiu_lrtime = baoxiu_lrtime;
        }

        public String getBaoxiu_name() {
            return baoxiu_name;
        }

        public void setBaoxiu_name(String baoxiu_name) {
            this.baoxiu_name = baoxiu_name;
        }

        public String getBaoxiu_paidanren() {
            return baoxiu_paidanren;
        }

        public void setBaoxiu_paidanren(String baoxiu_paidanren) {
            this.baoxiu_paidanren = baoxiu_paidanren;
        }

        public String getBaoxiu_paidantime() {
            return baoxiu_paidantime;
        }

        public void setBaoxiu_paidantime(String baoxiu_paidantime) {
            this.baoxiu_paidantime = baoxiu_paidantime;
        }

        public double getBaoxiu_qitafei() {
            return baoxiu_qitafei;
        }

        public void setBaoxiu_qitafei(double baoxiu_qitafei) {
            this.baoxiu_qitafei = baoxiu_qitafei;
        }

        public double getBaoxiu_rengongfei() {
            return baoxiu_rengongfei;
        }

        public void setBaoxiu_rengongfei(double baoxiu_rengongfei) {
            this.baoxiu_rengongfei = baoxiu_rengongfei;
        }

        public double getBaoxiu_shuijinfei() {
            return baoxiu_shuijinfei;
        }

        public void setBaoxiu_shuijinfei(double baoxiu_shuijinfei) {
            this.baoxiu_shuijinfei = baoxiu_shuijinfei;
        }

        public String getBaoxiu_status() {
            return baoxiu_status;
        }

        public void setBaoxiu_status(String baoxiu_status) {
            this.baoxiu_status = baoxiu_status;
        }

        public String getBaoxiu_weiwaidanweiimg() {
            return baoxiu_weiwaidanweiimg;
        }

        public void setBaoxiu_weiwaidanweiimg(String baoxiu_weiwaidanweiimg) {
            this.baoxiu_weiwaidanweiimg = baoxiu_weiwaidanweiimg;
        }

        public String getBaoxiu_weixiudanwei() {
            return baoxiu_weixiudanwei;
        }

        public void setBaoxiu_weixiudanwei(String baoxiu_weixiudanwei) {
            this.baoxiu_weixiudanwei = baoxiu_weixiudanwei;
        }

        public String getBaoxiu_weixiupaigongdan() {
            return baoxiu_weixiupaigongdan;
        }

        public void setBaoxiu_weixiupaigongdan(String baoxiu_weixiupaigongdan) {
            this.baoxiu_weixiupaigongdan = baoxiu_weixiupaigongdan;
        }

        public String getBaoxiu_xiangmu() {
            return baoxiu_xiangmu;
        }

        public void setBaoxiu_xiangmu(String baoxiu_xiangmu) {
            this.baoxiu_xiangmu = baoxiu_xiangmu;
        }

        public String getBaoxiu_xingzhi() {
            return baoxiu_xingzhi;
        }

        public void setBaoxiu_xingzhi(String baoxiu_xingzhi) {
            this.baoxiu_xingzhi = baoxiu_xingzhi;
        }

        public String getBaoxiu_zx_bumen() {
            return baoxiu_zx_bumen;
        }

        public void setBaoxiu_zx_bumen(String baoxiu_zx_bumen) {
            this.baoxiu_zx_bumen = baoxiu_zx_bumen;
        }

        public int getBaoxiu_zx_bumenid() {
            return baoxiu_zx_bumenid;
        }

        public void setBaoxiu_zx_bumenid(int baoxiu_zx_bumenid) {
            this.baoxiu_zx_bumenid = baoxiu_zx_bumenid;
        }

        public String getBaoxiu_zx_fuzeren() {
            return baoxiu_zx_fuzeren;
        }

        public void setBaoxiu_zx_fuzeren(String baoxiu_zx_fuzeren) {
            this.baoxiu_zx_fuzeren = baoxiu_zx_fuzeren;
        }

        public int getBaoxiu_zx_fuzerenid() {
            return baoxiu_zx_fuzerenid;
        }

        public void setBaoxiu_zx_fuzerenid(int baoxiu_zx_fuzerenid) {
            this.baoxiu_zx_fuzerenid = baoxiu_zx_fuzerenid;
        }

        public String getBaoxiu_zx_weixiuren() {
            return baoxiu_zx_weixiuren;
        }

        public void setBaoxiu_zx_weixiuren(String baoxiu_zx_weixiuren) {
            this.baoxiu_zx_weixiuren = baoxiu_zx_weixiuren;
        }

        public int getBaoxiu_zx_weixiurenid() {
            return baoxiu_zx_weixiurenid;
        }

        public void setBaoxiu_zx_weixiurenid(int baoxiu_zx_weixiurenid) {
            this.baoxiu_zx_weixiurenid = baoxiu_zx_weixiurenid;
        }

        public String getBaoxiu_zx_weixiurenphone() {
            return baoxiu_zx_weixiurenphone;
        }

        public void setBaoxiu_zx_weixiurenphone(String baoxiu_zx_weixiurenphone) {
            this.baoxiu_zx_weixiurenphone = baoxiu_zx_weixiurenphone;
        }
    }
}