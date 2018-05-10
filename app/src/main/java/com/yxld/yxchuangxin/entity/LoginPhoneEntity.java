package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * Created by yishangfei on 2017/3/2 0002.
 * 邮箱：yishangfei@foxmail.com
 */
public class LoginPhoneEntity extends BaseEntity {

    /**
     * total : 2
     * status : 0
     * msg : 查找成功
     * success : true
     * rows : [{"xiangmuCyId":1,"xiangmuId":346,"xiangmuIsUse":0,"xiangmuLatitude":"28.1524375082","xiangmuLongitude":"112.9853461721","xiangmuLoupan":"中远公馆","xiangmuTelphone":"123456789","xiangmuZimupingyin":"zhongyuangongguan"},{"xiangmuCyId":1,"xiangmuId":321,"xiangmuIsUse":0,"xiangmuLatitude":"28.1248055331","xiangmuLongitude":"113.0034477523","xiangmuLoupan":"凯姆国际","xiangmuTelphone":"000000","xiangmuZimupingyin":"kaimuguoji"}]
     */

    private List<LoginPhoneEntity> rows;

    public List<LoginPhoneEntity> getRows() {
        return rows;
    }

    public void setRows(List<LoginPhoneEntity> rows) {
        this.rows = rows;
    }

        /**
         * xiangmuCyId : 1
         * xiangmuId : 346
         * xiangmuIsUse : 0
         * xiangmuLatitude : 28.1524375082
         * xiangmuLongitude : 112.9853461721
         * xiangmuLoupan : 中远公馆
         * xiangmuTelphone : 123456789
         * xiangmuZimupingyin : zhongyuangongguan
         */

        private int xiangmuCyId;
        private int xiangmuId;
        private int xiangmuIsUse;
        private String xiangmuLatitude;
        private String xiangmuLongitude;
        private String xiangmuLoupan;
        private String xiangmuTelphone;
        private String xiangmuZimupingyin;

        public int getXiangmuCyId() {
            return xiangmuCyId;
        }

        public void setXiangmuCyId(int xiangmuCyId) {
            this.xiangmuCyId = xiangmuCyId;
        }

        public int getXiangmuId() {
            return xiangmuId;
        }

        public void setXiangmuId(int xiangmuId) {
            this.xiangmuId = xiangmuId;
        }

        public int getXiangmuIsUse() {
            return xiangmuIsUse;
        }

        public void setXiangmuIsUse(int xiangmuIsUse) {
            this.xiangmuIsUse = xiangmuIsUse;
        }

        public String getXiangmuLatitude() {
            return xiangmuLatitude;
        }

        public void setXiangmuLatitude(String xiangmuLatitude) {
            this.xiangmuLatitude = xiangmuLatitude;
        }

        public String getXiangmuLongitude() {
            return xiangmuLongitude;
        }

        public void setXiangmuLongitude(String xiangmuLongitude) {
            this.xiangmuLongitude = xiangmuLongitude;
        }

        public String getXiangmuLoupan() {
            return xiangmuLoupan;
        }

        public void setXiangmuLoupan(String xiangmuLoupan) {
            this.xiangmuLoupan = xiangmuLoupan;
        }

        public String getXiangmuTelphone() {
            return xiangmuTelphone;
        }

        public void setXiangmuTelphone(String xiangmuTelphone) {
            this.xiangmuTelphone = xiangmuTelphone;
        }

        public String getXiangmuZimupingyin() {
            return xiangmuZimupingyin;
        }

        public void setXiangmuZimupingyin(String xiangmuZimupingyin) {
            this.xiangmuZimupingyin = xiangmuZimupingyin;
        }
}
