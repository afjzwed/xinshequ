package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * 作者：Android on 2017/9/7
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class BaoJingEntity extends BaseEntity {

    /**
     * data : {"count":4,"rows":[{"baojing_chuli":"已处理","baojing_chuli_admin":"admin","baojing_chuli_shijian":1504809963000,"baojing_fangqu_bianhao":"1","baojing_fangqu_leixin":"普通防区","baojing_id":1,"baojing_shijian":1504804671000,"baojing_zhuji_mac":"139A31373138","shebei_name":"门磁"},{"baojing_fangqu_bianhao":"1","baojing_fangqu_leixin":"普通防区","baojing_id":2,"baojing_shijian":1504809907000,"baojing_zhuji_mac":"139A31373138","shebei_name":"门磁"},{"baojing_fangqu_bianhao":"1","baojing_fangqu_leixin":"普通防区","baojing_id":4,"baojing_shijian":1504810247000,"baojing_zhuji_mac":"139A31373138","shebei_name":"门磁"},{"baojing_fangqu_bianhao":"2","baojing_fangqu_leixin":"普通防区","baojing_id":3,"baojing_shijian":1504810244000,"baojing_zhuji_mac":"139A31373138","shebei_name":"红外1"}]}
     * success : true
     */

    private DataBean data;
    private boolean success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * count : 4
         * rows : [{"baojing_chuli":"已处理","baojing_chuli_admin":"admin","baojing_chuli_shijian":1504809963000,"baojing_fangqu_bianhao":"1","baojing_fangqu_leixin":"普通防区","baojing_id":1,"baojing_shijian":1504804671000,"baojing_zhuji_mac":"139A31373138","shebei_name":"门磁"},{"baojing_fangqu_bianhao":"1","baojing_fangqu_leixin":"普通防区","baojing_id":2,"baojing_shijian":1504809907000,"baojing_zhuji_mac":"139A31373138","shebei_name":"门磁"},{"baojing_fangqu_bianhao":"1","baojing_fangqu_leixin":"普通防区","baojing_id":4,"baojing_shijian":1504810247000,"baojing_zhuji_mac":"139A31373138","shebei_name":"门磁"},{"baojing_fangqu_bianhao":"2","baojing_fangqu_leixin":"普通防区","baojing_id":3,"baojing_shijian":1504810244000,"baojing_zhuji_mac":"139A31373138","shebei_name":"红外1"}]
         */

        private int count;
        private List<RowsBean> rows;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            private Integer baojingId;
            private String baojingZhujiMac;
            private String baojingFangquBianhao;
            private String baojingFangquLeixin;
            private String baojingShijian;
            private String baojingChuli;
            private String baojingChuliShijian;
            private String baojingChuliAdmin;
            private String baojingFangquMingzi;

            public Integer getBaojingId() {
                return baojingId;
            }

            public void setBaojingId(Integer baojingId) {
                this.baojingId = baojingId;
            }

            public String getBaojingZhujiMac() {
                return baojingZhujiMac;
            }

            public void setBaojingZhujiMac(String baojingZhujiMac) {
                this.baojingZhujiMac = baojingZhujiMac;
            }

            public String getBaojingFangquBianhao() {
                return baojingFangquBianhao;
            }

            public void setBaojingFangquBianhao(String baojingFangquBianhao) {
                this.baojingFangquBianhao = baojingFangquBianhao;
            }

            public String getBaojingFangquLeixin() {
                return baojingFangquLeixin;
            }

            public void setBaojingFangquLeixin(String baojingFangquLeixin) {
                this.baojingFangquLeixin = baojingFangquLeixin;
            }

            public String getBaojingShijian() {
                return baojingShijian;
            }

            public void setBaojingShijian(String baojingShijian) {
                this.baojingShijian = baojingShijian;
            }

            public String getBaojingChuli() {
                return baojingChuli;
            }

            public void setBaojingChuli(String baojingChuli) {
                this.baojingChuli = baojingChuli;
            }

            public String getBaojingChuliShijian() {
                return baojingChuliShijian;
            }

            public void setBaojingChuliShijian(String baojingChuliShijian) {
                this.baojingChuliShijian = baojingChuliShijian;
            }

            public String getBaojingChuliAdmin() {
                return baojingChuliAdmin;
            }

            public void setBaojingChuliAdmin(String baojingChuliAdmin) {
                this.baojingChuliAdmin = baojingChuliAdmin;
            }

            public String getBaojingFangquMingzi() {
                return baojingFangquMingzi;
            }

            public void setBaojingFangquMingzi(String baojingFangquMingzi) {
                this.baojingFangquMingzi = baojingFangquMingzi;
            }
        }
    }
}
