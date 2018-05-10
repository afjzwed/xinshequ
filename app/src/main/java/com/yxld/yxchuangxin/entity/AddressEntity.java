package com.yxld.yxchuangxin.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.yxld.yxchuangxin.base.BaseEntity;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/23
 * @descprition:
 */

public class AddressEntity extends BaseEntity implements Parcelable{
    public String name;
    public String phoneNum;
    public String address;
    public boolean isChecked;
    public AddressEntity(){

    }

    protected AddressEntity(Parcel in) {
        name = in.readString();
        phoneNum = in.readString();
        address = in.readString();
        isChecked = in.readByte() != 0;
    }

    public static final Creator<AddressEntity> CREATOR = new Creator<AddressEntity>() {
        @Override
        public AddressEntity createFromParcel(Parcel in) {
            return new AddressEntity(in);
        }

        @Override
        public AddressEntity[] newArray(int size) {
            return new AddressEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phoneNum);
        dest.writeString(address);
        dest.writeByte((byte) (isChecked ? 1 : 0));
    }
}
