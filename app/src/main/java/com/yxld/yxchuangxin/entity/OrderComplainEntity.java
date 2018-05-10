package com.yxld.yxchuangxin.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/22
 * @descprition: 订单投诉实体类
 */

public class OrderComplainEntity extends BaseEntity implements Parcelable {
    /**
     * id : 202
     * tsjyDindanbianhao : 9924770881573755
     * tsjyFanwei :
     * tsjyName : 万文秀
     * tsjyNeirong : 哈哈哈哈水了
     * tsjyShangpingname :
     * tsjyShenghestat :
     * tsjyShijian : {"date":28,"day":3,"hours":15,"minutes":10,"month":5,"nanos":0,"seconds":47,"time":1498633847000,"timezoneOffset":-480,"year":117}
     * tsjyStat : 0
     * tsjyTest1 : 中远公馆
     * tsjyTest2 :
     * tsjyType : 0
     * tsjyXiangmuid : 346
     * tsjyZhipairen :
     * tssjyTest3 :
     * tssjyTest4 :
     * tssjyTest5 :
     * tssjyTest6 : 处理结果
     * yezhuId : 1647
     */
    private List<OrderComplainEntity> malllist;
    private int id;
    private String tsjyDindanbianhao;
    private String tsjyFanwei;
    private String tsjyName;
    private String tsjyNeirong;
    private String tsjyShangpingname;
    private String tsjyShenghestat;
    private TsjyShijianBean tsjyShijian;
    private String tsjyStat; //未处理0 处理中1 已处理2
    private String tsjyTest1;
    private String tsjyTest2;
    private String tsjyType;
    private int tsjyXiangmuid;
    private String tsjyZhipairen;
    private String tssjyTest3;
    private String tssjyTest4;
    private String tssjyTest5;
    private String tssjyTest6;
    private int yezhuId;

    protected OrderComplainEntity(Parcel in) {
        malllist = in.createTypedArrayList(OrderComplainEntity.CREATOR);
        id = in.readInt();
        tsjyDindanbianhao = in.readString();
        tsjyFanwei = in.readString();
        tsjyName = in.readString();
        tsjyNeirong = in.readString();
        tsjyShangpingname = in.readString();
        tsjyShenghestat = in.readString();
        tsjyShijian = in.readParcelable(TsjyShijianBean.class.getClassLoader());
        tsjyStat = in.readString();
        tsjyTest1 = in.readString();
        tsjyTest2 = in.readString();
        tsjyType = in.readString();
        tsjyXiangmuid = in.readInt();
        tsjyZhipairen = in.readString();
        tssjyTest3 = in.readString();
        tssjyTest4 = in.readString();
        tssjyTest5 = in.readString();
        tssjyTest6 = in.readString();
        yezhuId = in.readInt();
    }

    public static final Creator<OrderComplainEntity> CREATOR = new Creator<OrderComplainEntity>() {
        @Override
        public OrderComplainEntity createFromParcel(Parcel in) {
            return new OrderComplainEntity(in);
        }

        @Override
        public OrderComplainEntity[] newArray(int size) {
            return new OrderComplainEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTsjyDindanbianhao() {
        return tsjyDindanbianhao;
    }

    public void setTsjyDindanbianhao(String tsjyDindanbianhao) {
        this.tsjyDindanbianhao = tsjyDindanbianhao;
    }

    public String getTsjyFanwei() {
        return tsjyFanwei;
    }

    public void setTsjyFanwei(String tsjyFanwei) {
        this.tsjyFanwei = tsjyFanwei;
    }

    public String getTsjyName() {
        return tsjyName;
    }

    public void setTsjyName(String tsjyName) {
        this.tsjyName = tsjyName;
    }

    public String getTsjyNeirong() {
        return tsjyNeirong;
    }

    public void setTsjyNeirong(String tsjyNeirong) {
        this.tsjyNeirong = tsjyNeirong;
    }

    public String getTsjyShangpingname() {
        return tsjyShangpingname;
    }

    public void setTsjyShangpingname(String tsjyShangpingname) {
        this.tsjyShangpingname = tsjyShangpingname;
    }

    public String getTsjyShenghestat() {
        return tsjyShenghestat;
    }

    public void setTsjyShenghestat(String tsjyShenghestat) {
        this.tsjyShenghestat = tsjyShenghestat;
    }

    public TsjyShijianBean getTsjyShijian() {
        return tsjyShijian;
    }

    public void setTsjyShijian(TsjyShijianBean tsjyShijian) {
        this.tsjyShijian = tsjyShijian;
    }

    public String getTsjyStat() {
        return tsjyStat;
    }

    public void setTsjyStat(String tsjyStat) {
        this.tsjyStat = tsjyStat;
    }

    public String getTsjyTest1() {
        return tsjyTest1;
    }

    public void setTsjyTest1(String tsjyTest1) {
        this.tsjyTest1 = tsjyTest1;
    }

    public String getTsjyTest2() {
        return tsjyTest2;
    }

    public void setTsjyTest2(String tsjyTest2) {
        this.tsjyTest2 = tsjyTest2;
    }

    public String getTsjyType() {
        return tsjyType;
    }

    public void setTsjyType(String tsjyType) {
        this.tsjyType = tsjyType;
    }

    public int getTsjyXiangmuid() {
        return tsjyXiangmuid;
    }

    public void setTsjyXiangmuid(int tsjyXiangmuid) {
        this.tsjyXiangmuid = tsjyXiangmuid;
    }

    public String getTsjyZhipairen() {
        return tsjyZhipairen;
    }

    public void setTsjyZhipairen(String tsjyZhipairen) {
        this.tsjyZhipairen = tsjyZhipairen;
    }

    public String getTssjyTest3() {
        return tssjyTest3;
    }

    public void setTssjyTest3(String tssjyTest3) {
        this.tssjyTest3 = tssjyTest3;
    }

    public String getTssjyTest4() {
        return tssjyTest4;
    }

    public void setTssjyTest4(String tssjyTest4) {
        this.tssjyTest4 = tssjyTest4;
    }

    public String getTssjyTest5() {
        return tssjyTest5;
    }

    public void setTssjyTest5(String tssjyTest5) {
        this.tssjyTest5 = tssjyTest5;
    }

    public String getTssjyTest6() {
        return tssjyTest6;
    }

    public void setTssjyTest6(String tssjyTest6) {
        this.tssjyTest6 = tssjyTest6;
    }

    public int getYezhuId() {
        return yezhuId;
    }

    public void setYezhuId(int yezhuId) {
        this.yezhuId = yezhuId;
    }


    public List<OrderComplainEntity> getMalllist() {
        return malllist;
    }

    public void setMalllist(List<OrderComplainEntity> malllist) {
        this.malllist = malllist;
    }

    @Override
    public String toString() {
        return "OrderComplainEntity{" +
                "id=" + id +
                ", tsjyDindanbianhao='" + tsjyDindanbianhao + '\'' +
                ", tsjyFanwei='" + tsjyFanwei + '\'' +
                ", tsjyName='" + tsjyName + '\'' +
                ", tsjyNeirong='" + tsjyNeirong + '\'' +
                ", tsjyShangpingname='" + tsjyShangpingname + '\'' +
                ", tsjyShenghestat='" + tsjyShenghestat + '\'' +
                ", tsjyShijian=" + tsjyShijian +
                ", tsjyStat='" + tsjyStat + '\'' +
                ", tsjyTest1='" + tsjyTest1 + '\'' +
                ", tsjyTest2='" + tsjyTest2 + '\'' +
                ", tsjyType='" + tsjyType + '\'' +
                ", tsjyXiangmuid=" + tsjyXiangmuid +
                ", tsjyZhipairen='" + tsjyZhipairen + '\'' +
                ", tssjyTest3='" + tssjyTest3 + '\'' +
                ", tssjyTest4='" + tssjyTest4 + '\'' +
                ", tssjyTest5='" + tssjyTest5 + '\'' +
                ", tssjyTest6='" + tssjyTest6 + '\'' +
                ", yezhuId=" + yezhuId +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(malllist);
        dest.writeInt(id);
        dest.writeString(tsjyDindanbianhao);
        dest.writeString(tsjyFanwei);
        dest.writeString(tsjyName);
        dest.writeString(tsjyNeirong);
        dest.writeString(tsjyShangpingname);
        dest.writeString(tsjyShenghestat);
        dest.writeParcelable(tsjyShijian, flags);
        dest.writeString(tsjyStat);
        dest.writeString(tsjyTest1);
        dest.writeString(tsjyTest2);
        dest.writeString(tsjyType);
        dest.writeInt(tsjyXiangmuid);
        dest.writeString(tsjyZhipairen);
        dest.writeString(tssjyTest3);
        dest.writeString(tssjyTest4);
        dest.writeString(tssjyTest5);
        dest.writeString(tssjyTest6);
        dest.writeInt(yezhuId);
    }

    public static class TsjyShijianBean implements Parcelable{
        /**
         * date : 28
         * day : 3
         * hours : 15
         * minutes : 10
         * month : 5
         * nanos : 0
         * seconds : 47
         * time : 1498633847000
         * timezoneOffset : -480
         * year : 117
         */

        private int date;
        private int day;
        private int hours;
        private int minutes;
        private int month;
        private int nanos;
        private int seconds;
        private long time;
        private int timezoneOffset;
        private int year;

        protected TsjyShijianBean(Parcel in) {
            date = in.readInt();
            day = in.readInt();
            hours = in.readInt();
            minutes = in.readInt();
            month = in.readInt();
            nanos = in.readInt();
            seconds = in.readInt();
            time = in.readLong();
            timezoneOffset = in.readInt();
            year = in.readInt();
        }

        public static final Creator<TsjyShijianBean> CREATOR = new Creator<TsjyShijianBean>() {
            @Override
            public TsjyShijianBean createFromParcel(Parcel in) {
                return new TsjyShijianBean(in);
            }

            @Override
            public TsjyShijianBean[] newArray(int size) {
                return new TsjyShijianBean[size];
            }
        };

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getNanos() {
            return nanos;
        }

        public void setNanos(int nanos) {
            this.nanos = nanos;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public void setTimezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(date);
            dest.writeInt(day);
            dest.writeInt(hours);
            dest.writeInt(minutes);
            dest.writeInt(month);
            dest.writeInt(nanos);
            dest.writeInt(seconds);
            dest.writeLong(time);
            dest.writeInt(timezoneOffset);
            dest.writeInt(year);
        }
    }
}
