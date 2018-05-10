package com.yxld.yxchuangxin.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.yxld.yxchuangxin.base.BaseEntity;

/**
 * @ClassName: StateOrderNum
 * @Description: 订单状态数量
 * @author wwx
 * @date 20170309下午3:28:22
 *
 */
public class StateOrderNum extends BaseEntity implements Parcelable{

	//{"MSG":"SUCCESS","daiFuKuan":343,"daiShouHuo":2,"daiPingJia":2,"quanbu":648,"tuiKuan":170,"daiFahuo":4,"status":"0"}


	private int daiFukuan = 0;
	private int daiShouhuo = 0;
	private int daiPingjia = 0;
	private int daiFahuo = 0;

	protected StateOrderNum(Parcel in) {
		daiFukuan = in.readInt();
		daiShouhuo = in.readInt();
		daiPingjia = in.readInt();
		daiFahuo = in.readInt();
	}

	public static final Creator<StateOrderNum> CREATOR = new Creator<StateOrderNum>() {
		@Override
		public StateOrderNum createFromParcel(Parcel in) {
			return new StateOrderNum(in);
		}

		@Override
		public StateOrderNum[] newArray(int size) {
			return new StateOrderNum[size];
		}
	};

	public int getDaiFukuan() {
		return daiFukuan;
	}

	public void setDaiFukuan(int daiFukuan) {
		this.daiFukuan = daiFukuan;
	}

	public int getDaiShouhuo() {
		return daiShouhuo;
	}

	public void setDaiShouhuo(int daiShouhuo) {
		this.daiShouhuo = daiShouhuo;
	}

	public int getDaiPingjia() {
		return daiPingjia;
	}

	public void setDaiPingjia(int daiPingjia) {
		this.daiPingjia = daiPingjia;
	}

	public int getDaiFahuo() {
		return daiFahuo;
	}

	public void setDaiFahuo(int daiFahuo) {
		this.daiFahuo = daiFahuo;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(daiFukuan);
		dest.writeInt(daiShouhuo);
		dest.writeInt(daiPingjia);
		dest.writeInt(daiFahuo);
	}
}