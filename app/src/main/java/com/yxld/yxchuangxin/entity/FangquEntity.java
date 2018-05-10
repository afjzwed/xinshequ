package com.yxld.yxchuangxin.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * 作者：Android on 2017/9/7
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class FangquEntity extends BaseEntity {

    /**
     * data : [{"baojingShijian":"2017-09-07 00:08:51","isAlarm":"1","shebeiBeizhu":"","shebeiFangquBianhao":"1","shebeiFangquLeixin":"1","shebeiId":3,"shebeiMingliKaiguan":"1","shebeiName":"哈哈哈哈哈哈哈","shebeiXuexiAdmin":"庾亮","shebeiXuexiShijian":"2017-09-07 00:05:17.0","shebeiZhujiMac":"139A31373138"},{"isAlarm":"0","shebeiBeizhu":"","shebeiFangquBianhao":"2","shebeiFangquLeixin":"2","shebeiId":4,"shebeiMingliKaiguan":"1","shebeiName":"哈哈哈哈哈哈哈","shebeiXuexiAdmin":"庾亮","shebeiXuexiShijian":"2017-09-07 00:17:10.0","shebeiZhujiMac":"139A31373138"},{"baojingShijian":"2017-09-07 01:01:58","isAlarm":"1","shebeiBeizhu":"","shebeiFangquBianhao":"3","shebeiFangquLeixin":"0","shebeiId":5,"shebeiMingliKaiguan":"1","shebeiName":"哈哈哈哈哈哈哈","shebeiXuexiAdmin":"庾亮","shebeiXuexiShijian":"2017-09-07 00:20:12.0","shebeiZhujiMac":"139A31373138"}]
     * success : true
     */

    private boolean success;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable{
        /**
         * baojingShijian : 2017-09-07 00:08:51
         * isAlarm : 1
         * shebeiBeizhu :
         * shebeiFangquBianhao : 1
         * shebeiFangquLeixin : 1
         * shebeiId : 3
         * shebeiMingliKaiguan : 1
         * shebeiName : 哈哈哈哈哈哈哈
         * shebeiXuexiAdmin : 庾亮
         * shebeiXuexiShijian : 2017-09-07 00:05:17.0
         * shebeiZhujiMac : 139A31373138
         */

        private String baojingShijian;
        private String isAlarm;
        private String shebeiBeizhu;
        private String shebeiFangquBianhao;
        private String shebeiFangquLeixin;
        private int shebeiId;
        private String shebeiMingliKaiguan;
        private String shebeiName;
        private String shebeiXuexiAdmin;
        private String shebeiXuexiShijian;
        private String shebeiZhujiMac;
        private boolean isCheck = false;

        protected DataBean(Parcel in) {
            baojingShijian = in.readString();
            isAlarm = in.readString();
            shebeiBeizhu = in.readString();
            shebeiFangquBianhao = in.readString();
            shebeiFangquLeixin = in.readString();
            shebeiId = in.readInt();
            shebeiMingliKaiguan = in.readString();
            shebeiName = in.readString();
            shebeiXuexiAdmin = in.readString();
            shebeiXuexiShijian = in.readString();
            shebeiZhujiMac = in.readString();
            isCheck = in.readByte() != 0;
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public String getBaojingShijian() {
            return baojingShijian;
        }

        public void setBaojingShijian(String baojingShijian) {
            this.baojingShijian = baojingShijian;
        }

        public String getIsAlarm() {
            return isAlarm;
        }

        public void setIsAlarm(String isAlarm) {
            this.isAlarm = isAlarm;
        }

        public String getShebeiBeizhu() {
            return shebeiBeizhu;
        }

        public void setShebeiBeizhu(String shebeiBeizhu) {
            this.shebeiBeizhu = shebeiBeizhu;
        }

        public String getShebeiFangquBianhao() {
            return shebeiFangquBianhao;
        }

        public void setShebeiFangquBianhao(String shebeiFangquBianhao) {
            this.shebeiFangquBianhao = shebeiFangquBianhao;
        }

        public String getShebeiFangquLeixin() {
            return shebeiFangquLeixin;
        }

        public void setShebeiFangquLeixin(String shebeiFangquLeixin) {
            this.shebeiFangquLeixin = shebeiFangquLeixin;
        }

        public int getShebeiId() {
            return shebeiId;
        }

        public void setShebeiId(int shebeiId) {
            this.shebeiId = shebeiId;
        }

        public String getShebeiMingliKaiguan() {
            return shebeiMingliKaiguan;
        }

        public void setShebeiMingliKaiguan(String shebeiMingliKaiguan) {
            this.shebeiMingliKaiguan = shebeiMingliKaiguan;
        }

        public String getShebeiName() {
            return shebeiName;
        }

        public void setShebeiName(String shebeiName) {
            this.shebeiName = shebeiName;
        }

        public String getShebeiXuexiAdmin() {
            return shebeiXuexiAdmin;
        }

        public void setShebeiXuexiAdmin(String shebeiXuexiAdmin) {
            this.shebeiXuexiAdmin = shebeiXuexiAdmin;
        }

        public String getShebeiXuexiShijian() {
            return shebeiXuexiShijian;
        }

        public void setShebeiXuexiShijian(String shebeiXuexiShijian) {
            this.shebeiXuexiShijian = shebeiXuexiShijian;
        }

        public String getShebeiZhujiMac() {
            return shebeiZhujiMac;
        }

        public void setShebeiZhujiMac(String shebeiZhujiMac) {
            this.shebeiZhujiMac = shebeiZhujiMac;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(baojingShijian);
            dest.writeString(isAlarm);
            dest.writeString(shebeiBeizhu);
            dest.writeString(shebeiFangquBianhao);
            dest.writeString(shebeiFangquLeixin);
            dest.writeInt(shebeiId);
            dest.writeString(shebeiMingliKaiguan);
            dest.writeString(shebeiName);
            dest.writeString(shebeiXuexiAdmin);
            dest.writeString(shebeiXuexiShijian);
            dest.writeString(shebeiZhujiMac);
            dest.writeByte((byte) (isCheck ? 1 : 0));
        }
    }
}
