package com.yxld.yxchuangxin.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.yxld.yxchuangxin.base.BaseEntity;

/**
 * @ClassName: YezhuDainZhiQuan
 * @Description: 获取业主电子券数量
 * @author wwx
 * @date 20170323
 *
 */
public class YezhuDainZhiQuan extends BaseEntity implements Parcelable {

	public int curPeisongFei;//当前配送费
	public int freePrice;//满减金额
	public int maxPeisongPrice; //大件配送费
	public int minPeisongPrice;//普通配送费
	public int total; //最多可用电子券数量

	protected YezhuDainZhiQuan(Parcel in) {
		curPeisongFei = in.readInt();
		freePrice = in.readInt();
		maxPeisongPrice = in.readInt();
		minPeisongPrice = in.readInt();
		total = in.readInt();
	}

	public static final Creator<YezhuDainZhiQuan> CREATOR = new Creator<YezhuDainZhiQuan>() {
		@Override
		public YezhuDainZhiQuan createFromParcel(Parcel in) {
			return new YezhuDainZhiQuan(in);
		}

		@Override
		public YezhuDainZhiQuan[] newArray(int size) {
			return new YezhuDainZhiQuan[size];
		}
	};

	public int getCurPeisongFei() {
		return curPeisongFei;
	}

	public void setCurPeisongFei(int curPeisongFei) {
		this.curPeisongFei = curPeisongFei;
	}

	public int getFreePrice() {
		return freePrice;
	}

	public void setFreePrice(int freePrice) {
		this.freePrice = freePrice;
	}

	public int getMaxPeisongPrice() {
		return maxPeisongPrice;
	}

	public void setMaxPeisongPrice(int maxPeisongPrice) {
		this.maxPeisongPrice = maxPeisongPrice;
	}

	public int getMinPeisongPrice() {
		return minPeisongPrice;
	}

	public void setMinPeisongPrice(int minPeisongPrice) {
		this.minPeisongPrice = minPeisongPrice;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(curPeisongFei);
		dest.writeInt(freePrice);
		dest.writeInt(maxPeisongPrice);
		dest.writeInt(minPeisongPrice);
		dest.writeInt(total);
	}
}
