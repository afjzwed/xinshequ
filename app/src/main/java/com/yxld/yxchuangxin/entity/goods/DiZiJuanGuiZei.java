package com.yxld.yxchuangxin.entity.goods;

import com.yxld.yxchuangxin.base.BaseEntity;

/**
 * @author xlei
 * @Date 2017/12/20.
 */

public class DiZiJuanGuiZei extends BaseEntity {
    private DataBean rows;

    public DataBean getRows() {
        return rows;
    }

    public void setRows(DataBean rows) {
        this.rows = rows;
    }

    public static class DataBean {
        private int balance;
        private int useTicket;
        private String explain;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public int getUseTicket() {
            return useTicket;
        }

        public void setUseTicket(int useTicket) {
            this.useTicket = useTicket;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }
    }
}
