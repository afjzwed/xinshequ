package com.yxld.yxchuangxin.entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

public class ShopList extends BaseEntity implements Parcelable {
	
	private int total;

	public List<CxwyMallProduct> productList;

	protected ShopList(Parcel in) {
		total = in.readInt();
		productList = in.createTypedArrayList(CxwyMallProduct.CREATOR);
	}

	public static final Creator<ShopList> CREATOR = new Creator<ShopList>() {
		@Override
		public ShopList createFromParcel(Parcel in) {
			return new ShopList(in);
		}

		@Override
		public ShopList[] newArray(int size) {
			return new ShopList[size];
		}
	};

	public List<CxwyMallProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<CxwyMallProduct> productList) {
		this.productList = productList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "ShopList [total=" + total + ", productList=" + productList
				+ ", status=" + status + ", MSG=" + MSG + "]";
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(total);
		dest.writeTypedList(productList);
	}
}
