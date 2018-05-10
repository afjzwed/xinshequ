package com.yxld.yxchuangxin.entity.goods;

import com.yxld.yxchuangxin.base.BaseEntity;

/**
 * @author xlei
 * @Date 2017/10/27.
 */

public class ShopDianZiJuan extends BaseEntity {
    private int total;
    private Dianzijuan rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Dianzijuan getRows() {
        return rows;
    }

    public void setRows(Dianzijuan rows) {
        this.rows = rows;
    }



    public static class Dianzijuan {
        private int balance;
        private PeiSongFeiRule peisongfeiRule;
        private VoucherRule voucherRule;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public PeiSongFeiRule getPeisongfeiRule() {
            return peisongfeiRule;
        }

        public void setPeisongfeiRule(PeiSongFeiRule peisongfeiRule) {
            this.peisongfeiRule = peisongfeiRule;
        }

        public VoucherRule getVoucherRule() {
            return voucherRule;
        }

        public void setVoucherRule(VoucherRule voucherRule) {
            this.voucherRule = voucherRule;
        }

        public static class VoucherRule {
            private int id;
            private double voucherRuleMinPrice;
            private double voucherRuleUsePrice;
            private int voucherRuleXmId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public double getVoucherRuleMinPrice() {
                return voucherRuleMinPrice;
            }

            public void setVoucherRuleMinPrice(double voucherRuleMinPrice) {
                this.voucherRuleMinPrice = voucherRuleMinPrice;
            }

            public double getVoucherRuleUsePrice() {
                return voucherRuleUsePrice;
            }

            public void setVoucherRuleUsePrice(double voucherRuleUsePrice) {
                this.voucherRuleUsePrice = voucherRuleUsePrice;
            }

            public int getVoucherRuleXmId() {
                return voucherRuleXmId;
            }

            public void setVoucherRuleXmId(int voucherRuleXmId) {
                this.voucherRuleXmId = voucherRuleXmId;
            }
        }

        public static class PeiSongFeiRule {

            private double freePrice;
            private int gongsiId;
            private int id;
            private double maxPeisongPrice;
            private double minPeisongPrice;
            private String xiangmuLoupan;
            private int xmId;
            private int peisongMethod;

            public int getPeisongMethod() {
                return peisongMethod;
            }

            public void setPeisongMethod(int peisongMethod) {
                this.peisongMethod = peisongMethod;
            }

            public double getFreePrice() {
                return freePrice;
            }

            public void setFreePrice(double freePrice) {
                this.freePrice = freePrice;
            }

            public int getGongsiId() {
                return gongsiId;
            }

            public void setGongsiId(int gongsiId) {
                this.gongsiId = gongsiId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public double getMaxPeisongPrice() {
                return maxPeisongPrice;
            }

            public void setMaxPeisongPrice(double maxPeisongPrice) {
                this.maxPeisongPrice = maxPeisongPrice;
            }

            public double getMinPeisongPrice() {
                return minPeisongPrice;
            }

            public void setMinPeisongPrice(double minPeisongPrice) {
                this.minPeisongPrice = minPeisongPrice;
            }

            public String getXiangmuLoupan() {
                return xiangmuLoupan;
            }

            public void setXiangmuLoupan(String xiangmuLoupan) {
                this.xiangmuLoupan = xiangmuLoupan;
            }

            public int getXmId() {
                return xmId;
            }

            public void setXmId(int xmId) {
                this.xmId = xmId;
            }
        }
    }


}
