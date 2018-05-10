package com.yxld.yxchuangxin.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * 作者：Android on 2017/9/6
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class HostEntiti extends BaseEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
//        {
//            "zhujiBaojingZhuangtai":"0", "zhujiBeizhu":"", "zhujiDanyuan":"888", "zhujiFanghao":
//            "888", "zhujiGongsiId":1, "zhujiId":13, "zhujiIfYezhu":"1", "zhujiJiaruAdmin":
//            "左佳久", "zhujiJiaruShijian":"2017-10-13 19:02:08.0", "zhujiJiezhiTime":
//            "2018-10-13 19:02:08.0", "zhujiLeixin":2, "zhujiLoudong":"888", "zhujiMac":
//            "139A31373138", "zhujiShebeiName":"fdsfs", "zhujiXiangmuId":"346", "zhujiYonghuLeixin":
//            1, "zhujiZaixianZhuangtai":"1"
//        }

        /**
         * zhujiBaojingZhuangtai : 0
         * zhujiBuchefangZhuangtai : 2
         * zhujiDanyuan : 3
         * zhujiFanghao : 333
         * zhujiGongsiId : 1
         * zhujiId : 2
         * zhujiJiaruAdmin : 庾亮
         * zhujiJiaruShijian : 2017-09-06 18:37:34.0
         * zhujiLoudong : 3
         * zhujiMac : 139A31373138
         * zhujiMingdiZhuangtai : 0
         * zhujiShebeiName : 哈哈哈哈哈哈哈
         * zhujiXiangmuId : 346
         * zhujiZaixianZhuangtai : 1
         * zhujiYonghuLeixin:1
         * zhujiJiezhiTime
         */

        private String zhujiBaojingZhuangtai;    //主机报警状态，1报警 0未报警
        private String zhujiBuchefangZhuangtai;  //布撤防状态，0撤防 1布防 2留守布防
        private String zhujiDanyuan;
        private String zhujiFanghao;
        private int zhujiGongsiId;
        private int zhujiId;
        private String zhujiJiaruAdmin;
        private String zhujiJiaruShijian;
        private String zhujiLoudong;
        private String zhujiMac;
        private String zhujiMingdiZhuangtai;       //鸣笛状态0未鸣笛 1鸣笛
        private String zhujiShebeiName;
        private String zhujiXiangmuId;
        private String zhujiZaixianZhuangtai;        //在线状态 1在线，0未在线
        private String zhujiIfYezhu;
        private int zhujiYonghuLeixin;
        private String zhujiJiezhiTime;

        protected DataBean(Parcel in) {
            zhujiBaojingZhuangtai = in.readString();
            zhujiBuchefangZhuangtai = in.readString();
            zhujiDanyuan = in.readString();
            zhujiFanghao = in.readString();
            zhujiGongsiId = in.readInt();
            zhujiId = in.readInt();
            zhujiJiaruAdmin = in.readString();
            zhujiJiaruShijian = in.readString();
            zhujiLoudong = in.readString();
            zhujiMac = in.readString();
            zhujiMingdiZhuangtai = in.readString();
            zhujiShebeiName = in.readString();
            zhujiXiangmuId = in.readString();
            zhujiZaixianZhuangtai = in.readString();
            zhujiIfYezhu = in.readString();
            zhujiYonghuLeixin = in.readInt();
            zhujiJiezhiTime = in.readString();
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

        public String getZhujiIfYezhu() {
            return zhujiIfYezhu;
        }

        public void setZhujiIfYezhu(String zhujiIfYezhu) {
            this.zhujiIfYezhu = zhujiIfYezhu;
        }

        public String getZhujiBaojingZhuangtai() {
            return zhujiBaojingZhuangtai;
        }

        public void setZhujiBaojingZhuangtai(String zhujiBaojingZhuangtai) {
            this.zhujiBaojingZhuangtai = zhujiBaojingZhuangtai;
        }

        public String getZhujiBuchefangZhuangtai() {
            return zhujiBuchefangZhuangtai;
        }

        public void setZhujiBuchefangZhuangtai(String zhujiBuchefangZhuangtai) {
            this.zhujiBuchefangZhuangtai = zhujiBuchefangZhuangtai;
        }

        public String getZhujiDanyuan() {
            return zhujiDanyuan;
        }

        public void setZhujiDanyuan(String zhujiDanyuan) {
            this.zhujiDanyuan = zhujiDanyuan;
        }

        public String getZhujiFanghao() {
            return zhujiFanghao;
        }

        public void setZhujiFanghao(String zhujiFanghao) {
            this.zhujiFanghao = zhujiFanghao;
        }

        public int getZhujiGongsiId() {
            return zhujiGongsiId;
        }

        public void setZhujiGongsiId(int zhujiGongsiId) {
            this.zhujiGongsiId = zhujiGongsiId;
        }

        public int getZhujiId() {
            return zhujiId;
        }

        public void setZhujiId(int zhujiId) {
            this.zhujiId = zhujiId;
        }

        public String getZhujiJiaruAdmin() {
            return zhujiJiaruAdmin;
        }

        public void setZhujiJiaruAdmin(String zhujiJiaruAdmin) {
            this.zhujiJiaruAdmin = zhujiJiaruAdmin;
        }

        public String getZhujiJiaruShijian() {
            return zhujiJiaruShijian;
        }

        public void setZhujiJiaruShijian(String zhujiJiaruShijian) {
            this.zhujiJiaruShijian = zhujiJiaruShijian;
        }

        public String getZhujiLoudong() {
            return zhujiLoudong;
        }

        public void setZhujiLoudong(String zhujiLoudong) {
            this.zhujiLoudong = zhujiLoudong;
        }

        public String getZhujiMac() {
            return zhujiMac;
        }

        public void setZhujiMac(String zhujiMac) {
            this.zhujiMac = zhujiMac;
        }

        public String getZhujiMingdiZhuangtai() {
            return zhujiMingdiZhuangtai;
        }

        public void setZhujiMingdiZhuangtai(String zhujiMingdiZhuangtai) {
            this.zhujiMingdiZhuangtai = zhujiMingdiZhuangtai;
        }

        public String getZhujiShebeiName() {
            return zhujiShebeiName;
        }

        public void setZhujiShebeiName(String zhujiShebeiName) {
            this.zhujiShebeiName = zhujiShebeiName;
        }

        public String getZhujiXiangmuId() {
            return zhujiXiangmuId;
        }

        public void setZhujiXiangmuId(String zhujiXiangmuId) {
            this.zhujiXiangmuId = zhujiXiangmuId;
        }

        public String getZhujiZaixianZhuangtai() {
            return zhujiZaixianZhuangtai;
        }

        public void setZhujiZaixianZhuangtai(String zhujiZaixianZhuangtai) {
            this.zhujiZaixianZhuangtai = zhujiZaixianZhuangtai;
        }

        public int getZhujiYonghuLeixin() {
            return zhujiYonghuLeixin;
        }

        public void setZhujiYonghuLeixin(int zhujiYonghuLeixin) {
            this.zhujiYonghuLeixin = zhujiYonghuLeixin;
        }

        public String getZhujiJiezhiTime() {
            return zhujiJiezhiTime;
        }

        public void setZhujiJiezhiTime(String zhujiJiezhiTime) {
            this.zhujiJiezhiTime = zhujiJiezhiTime;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(zhujiBaojingZhuangtai);
            dest.writeString(zhujiBuchefangZhuangtai);
            dest.writeString(zhujiDanyuan);
            dest.writeString(zhujiFanghao);
            dest.writeInt(zhujiGongsiId);
            dest.writeInt(zhujiId);
            dest.writeString(zhujiJiaruAdmin);
            dest.writeString(zhujiJiaruShijian);
            dest.writeString(zhujiLoudong);
            dest.writeString(zhujiMac);
            dest.writeString(zhujiMingdiZhuangtai);
            dest.writeString(zhujiShebeiName);
            dest.writeString(zhujiXiangmuId);
            dest.writeString(zhujiZaixianZhuangtai);
            dest.writeString(zhujiIfYezhu);
            dest.writeInt(zhujiYonghuLeixin);
            dest.writeString(zhujiJiezhiTime);
        }
    }
}
