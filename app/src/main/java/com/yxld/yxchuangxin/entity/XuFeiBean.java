package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * @author Yuan.Y.Q
 * @Date 2017/10/10.
 */

public class XuFeiBean extends BaseEntity {
    private List<XuFeiContent> jichu;
    private List<XuFeiContent> zengzhi;
    private String shijian;

    public String getShijian() {
        return shijian;
    }

    public void setShijian(String shijian) {
        this.shijian = shijian;
    }

    public List<XuFeiContent> getJichu() {
        return jichu;
    }

    public void setJichu(List<XuFeiContent> jichu) {
        this.jichu = jichu;
    }

    public List<XuFeiContent> getZengzhi() {
        return zengzhi;
    }

    public void setZengzhi(List<XuFeiContent> zengzhi) {
        this.zengzhi = zengzhi;
    }

    public static class XuFeiContent {
        // {"beizhu":"测试数据","feiyongLeixin":2,"id":1,"money":66,"shixian":1,"yonghuLeixin":1,"zhujiLeixin":1}
        private String beizhu;
        private String feiyongLeixin;//1为增值2为流量
        private int id;
        private float money;
        private int shixian;
        private int yonghuLeixin;//1为租2为买
        private int zhujiLeixin;//1为流量2为wifi

        public String getBeizhu() {
            return beizhu;
        }

        public void setBeizhu(String beizhu) {
            this.beizhu = beizhu;
        }

        public String getFeiyongLeixin() {
            return feiyongLeixin;
        }

        public void setFeiyongLeixin(String feiyongLeixin) {
            this.feiyongLeixin = feiyongLeixin;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public float getMoney() {
            return money;
        }

        public void setMoney(float money) {
            this.money = money;
        }

        public int getShixian() {
            return shixian;
        }

        public void setShixian(int shixian) {
            this.shixian = shixian;
        }

        public int getYonghuLeixin() {
            return yonghuLeixin;
        }

        public void setYonghuLeixin(int yonghuLeixin) {
            this.yonghuLeixin = yonghuLeixin;
        }

        public int getZhujiLeixin() {
            return zhujiLeixin;
        }

        public void setZhujiLeixin(int zhujiLeixin) {
            this.zhujiLeixin = zhujiLeixin;
        }
    }
}
