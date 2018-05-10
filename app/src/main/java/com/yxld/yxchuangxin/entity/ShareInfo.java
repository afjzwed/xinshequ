package com.yxld.yxchuangxin.entity;

import android.graphics.Bitmap;

/**
 * @author WWX
 * 分享
 */
public class ShareInfo {
	public ShareInfo ShareInfo;

	/** 标题*/
	public String Title;

	/** 内容*/
	public String ShareCon;

	/** 图片路径*/
	public String ImgUrl;

	/** QQ图片路径(解决qq编码)*/
	public String QQImgUrl;

	public Bitmap bitmap;

	public ShareInfo() {
	}

	public ShareInfo(String title, String shareCon, String imgUrl, String QQImgUrl, Bitmap bitmap) {
		Title = title;
		ShareCon = shareCon;
		ImgUrl = imgUrl;
		this.QQImgUrl = QQImgUrl;
		this.bitmap = bitmap;
	}



	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getShareCon() {
		return ShareCon;
	}

	public void setShareCon(String shareCon) {
		ShareCon = shareCon;
	}

	public String getImgUrl() {
		return ImgUrl;
	}

	public void setImgUrl(String imgUrl) {
		ImgUrl = imgUrl;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public String getQQImgUrl() {
		return QQImgUrl;
	}

	public void setQQImgUrl(String QQImgUrl) {
		this.QQImgUrl = QQImgUrl;
	}

	@Override
	public String toString() {
		return "ShareInfo{" +
				"Title='" + Title + '\'' +
				", ShareCon='" + ShareCon + '\'' +
				", ImgUrl='" + ImgUrl + '\'' +
				", QQImgUrl='" + QQImgUrl + '\'' +
				", bitmap=" + bitmap +
				'}';
	}
}
