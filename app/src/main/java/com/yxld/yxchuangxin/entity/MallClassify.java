package com.yxld.yxchuangxin.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * 作者：Android on 2017/10/23
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class MallClassify extends BaseEntity  implements Parcelable{

    /**
     * success : true
     * rows : [{"id":1,"fenleiMing":"蔬菜","fenleiQiyong":1,"fenleiPaixu":1,"fenleiFuId":0,"fenleiTubiao":"upload/img/classify/3.JPEG","fenleiXiangmu":1,"fenleiGongsi":1,"xiangmuMing":null},{"id":7,"fenleiMing":"蔬菜","fenleiQiyong":1,"fenleiPaixu":1,"fenleiFuId":0,"fenleiTubiao":"/classify/4.JPEG","fenleiXiangmu":2,"fenleiGongsi":1,"xiangmuMing":null},{"id":9,"fenleiMing":"水果","fenleiQiyong":1,"fenleiPaixu":1,"fenleiFuId":0,"fenleiTubiao":"/classify/4.JPEG","fenleiXiangmu":2,"fenleiGongsi":1,"xiangmuMing":null},{"id":112,"fenleiMing":"饮料","fenleiQiyong":1,"fenleiPaixu":1,"fenleiFuId":0,"fenleiTubiao":"upload/img/classify/3.JPEG","fenleiXiangmu":1,"fenleiGongsi":1,"xiangmuMing":null},{"id":139,"fenleiMing":"调料","fenleiQiyong":1,"fenleiPaixu":1,"fenleiFuId":0,"fenleiTubiao":"upload/img/classify/3.JPEG","fenleiXiangmu":321,"fenleiGongsi":1,"xiangmuMing":null}]
     * total : null
     */

    private boolean success;
    private Object total;
    private List<RowsBean> rows;


    protected MallClassify(Parcel in) {
        success = in.readByte() != 0;
        rows = in.createTypedArrayList(RowsBean.CREATOR);
    }

    public static final Creator<MallClassify> CREATOR = new Creator<MallClassify>() {
        @Override
        public MallClassify createFromParcel(Parcel in) {
            return new MallClassify(in);
        }

        @Override
        public MallClassify[] newArray(int size) {
            return new MallClassify[size];
        }
    };

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeTypedList(rows);
    }


    public static class RowsBean implements Parcelable {
        /**
         * id : 1
         * fenleiMing : 蔬菜
         * fenleiQiyong : 1
         * fenleiPaixu : 1
         * fenleiFuId : 0
         * fenleiTubiao : upload/img/classify/3.JPEG
         * fenleiXiangmu : 1
         * fenleiGongsi : 1
         * xiangmuMing : null
         */

        private int id;
        private String fenleiMing;
        private int fenleiQiyong;
        private int fenleiPaixu;
        private int fenleiFuId;
        private String fenleiTubiao;
        private int fenleiXiangmu;
        private int fenleiGongsi;
        private Object xiangmuMing;
        public RowsBean(){};
        protected RowsBean(Parcel in) {
            id = in.readInt();
            fenleiMing = in.readString();
            fenleiQiyong = in.readInt();
            fenleiPaixu = in.readInt();
            fenleiFuId = in.readInt();
            fenleiTubiao = in.readString();
            fenleiXiangmu = in.readInt();
            fenleiGongsi = in.readInt();
        }

        public static final Creator<RowsBean> CREATOR = new Creator<RowsBean>() {
            @Override
            public RowsBean createFromParcel(Parcel in) {
                return new RowsBean(in);
            }

            @Override
            public RowsBean[] newArray(int size) {
                return new RowsBean[size];
            }
        };

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFenleiMing() {
            return fenleiMing;
        }

        public void setFenleiMing(String fenleiMing) {
            this.fenleiMing = fenleiMing;
        }

        public int getFenleiQiyong() {
            return fenleiQiyong;
        }

        public void setFenleiQiyong(int fenleiQiyong) {
            this.fenleiQiyong = fenleiQiyong;
        }

        public int getFenleiPaixu() {
            return fenleiPaixu;
        }

        public void setFenleiPaixu(int fenleiPaixu) {
            this.fenleiPaixu = fenleiPaixu;
        }

        public int getFenleiFuId() {
            return fenleiFuId;
        }

        public void setFenleiFuId(int fenleiFuId) {
            this.fenleiFuId = fenleiFuId;
        }

        public String getFenleiTubiao() {
            return fenleiTubiao;
        }

        public void setFenleiTubiao(String fenleiTubiao) {
            this.fenleiTubiao = fenleiTubiao;
        }

        public int getFenleiXiangmu() {
            return fenleiXiangmu;
        }

        public void setFenleiXiangmu(int fenleiXiangmu) {
            this.fenleiXiangmu = fenleiXiangmu;
        }

        public int getFenleiGongsi() {
            return fenleiGongsi;
        }

        public void setFenleiGongsi(int fenleiGongsi) {
            this.fenleiGongsi = fenleiGongsi;
        }

        public Object getXiangmuMing() {
            return xiangmuMing;
        }

        public void setXiangmuMing(Object xiangmuMing) {
            this.xiangmuMing = xiangmuMing;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(fenleiMing);
            dest.writeInt(fenleiQiyong);
            dest.writeInt(fenleiPaixu);
            dest.writeInt(fenleiFuId);
            dest.writeString(fenleiTubiao);
            dest.writeInt(fenleiXiangmu);
            dest.writeInt(fenleiGongsi);
        }
    }
}
