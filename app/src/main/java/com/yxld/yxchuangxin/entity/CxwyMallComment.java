package com.yxld.yxchuangxin.entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * CxwyMallComment entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CxwyMallComment extends BaseEntity implements Parcelable {

	private List<CxwyMallComment> commentList;
	private int total;

	/** 0 代表用户收藏 1 代表用户未收藏**/
	private int collection = 1;

    // Fields    
     private Integer pingjiaId;//id
     private Integer pingjiaLevel;//评价等级
     private Integer pingjiaShangpNum;//商品id
     private String pingjiaBody="";//评价内容
     private String pingjiaName;//用户名称
     private String pingjiaImgSrc1;//图片
     private String pingjiaImgSrc2;//图片
     private String pingjiaImgSrc3;//图片
     private String pingjiaImgSrc4;//图片
     private String pingjiaNowTime;//评价时间
     private String pingjiaUseTime;//订单使用时间
     private String pingjiaBeiyong1;//商品名称
     private String pingjiaBeiyong2;//订单id
     private String pingjiaBeiyong3;//订单编号
     private String pingjiaBeiyong4;//业主id
    // Constructors
    /** default constructor */
    public CxwyMallComment() {
    }


    /** full constructor */
    public CxwyMallComment(Integer pingjiaLevel, Integer pingjiaShangpNum, String pingjiaBody, String pingjiaName, String pingjiaImgSrc1, String pingjiaImgSrc2, String pingjiaImgSrc3, String pingjiaImgSrc4, String pingjiaNowTime, String pingjiaUseTime, String pingjiaBeiyong1, String pingjiaBeiyong2, String pingjiaBeiyong3, String pingjiaBeiyong4) {
        this.pingjiaLevel = pingjiaLevel;
        this.pingjiaShangpNum = pingjiaShangpNum;
        this.pingjiaBody = pingjiaBody;
        this.pingjiaName = pingjiaName;
        this.pingjiaImgSrc1 = pingjiaImgSrc1;
        this.pingjiaImgSrc2 = pingjiaImgSrc2;
        this.pingjiaImgSrc3 = pingjiaImgSrc3;
        this.pingjiaImgSrc4 = pingjiaImgSrc4;
        this.pingjiaNowTime = pingjiaNowTime;
        this.pingjiaUseTime = pingjiaUseTime;
        this.pingjiaBeiyong1 = pingjiaBeiyong1;
        this.pingjiaBeiyong2 = pingjiaBeiyong2;
        this.pingjiaBeiyong3 = pingjiaBeiyong3;
        this.pingjiaBeiyong4 = pingjiaBeiyong4;
    }


    // Property accessors

    protected CxwyMallComment(Parcel in) {
        commentList = in.createTypedArrayList(CxwyMallComment.CREATOR);
        total = in.readInt();
        collection = in.readInt();
        pingjiaBody = in.readString();
        pingjiaName = in.readString();
        pingjiaImgSrc1 = in.readString();
        pingjiaImgSrc2 = in.readString();
        pingjiaImgSrc3 = in.readString();
        pingjiaImgSrc4 = in.readString();
        pingjiaNowTime = in.readString();
        pingjiaUseTime = in.readString();
        pingjiaBeiyong1 = in.readString();
        pingjiaBeiyong2 = in.readString();
        pingjiaBeiyong3 = in.readString();
        pingjiaBeiyong4 = in.readString();
    }

    public static final Creator<CxwyMallComment> CREATOR = new Creator<CxwyMallComment>() {
        @Override
        public CxwyMallComment createFromParcel(Parcel in) {
            return new CxwyMallComment(in);
        }

        @Override
        public CxwyMallComment[] newArray(int size) {
            return new CxwyMallComment[size];
        }
    };

    public Integer getPingjiaId() {
        return this.pingjiaId;
    }

    public void setPingjiaId(Integer pingjiaId) {
        this.pingjiaId = pingjiaId;
    }

    public Integer getPingjiaLevel() {
        return this.pingjiaLevel;
    }

    public void setPingjiaLevel(Integer pingjiaLevel) {
        this.pingjiaLevel = pingjiaLevel;
    }

    public Integer getPingjiaShangpNum() {
        return this.pingjiaShangpNum;
    }

    public void setPingjiaShangpNum(Integer pingjiaShangpNum) {
        this.pingjiaShangpNum = pingjiaShangpNum;
    }

    public String getPingjiaBody() {
        return this.pingjiaBody;
    }

    public void setPingjiaBody(String pingjiaBody) {
        this.pingjiaBody = pingjiaBody;
    }

    public String getPingjiaName() {
        return this.pingjiaName;
    }

    public void setPingjiaName(String pingjiaName) {
        this.pingjiaName = pingjiaName;
    }

    public String getPingjiaImgSrc1() {
        return this.pingjiaImgSrc1;
    }

    public void setPingjiaImgSrc1(String pingjiaImgSrc1) {
        this.pingjiaImgSrc1 = pingjiaImgSrc1;
    }

    public String getPingjiaImgSrc2() {
        return this.pingjiaImgSrc2;
    }

    public void setPingjiaImgSrc2(String pingjiaImgSrc2) {
        this.pingjiaImgSrc2 = pingjiaImgSrc2;
    }

    public String getPingjiaImgSrc3() {
        return this.pingjiaImgSrc3;
    }

    public void setPingjiaImgSrc3(String pingjiaImgSrc3) {
        this.pingjiaImgSrc3 = pingjiaImgSrc3;
    }

    public String getPingjiaImgSrc4() {
        return this.pingjiaImgSrc4;
    }

    public void setPingjiaImgSrc4(String pingjiaImgSrc4) {
        this.pingjiaImgSrc4 = pingjiaImgSrc4;
    }

    public String getPingjiaNowTime() {
        return this.pingjiaNowTime;
    }

    public void setPingjiaNowTime(String pingjiaNowTime) {
        this.pingjiaNowTime = pingjiaNowTime;
    }

    public String getPingjiaUseTime() {
        return this.pingjiaUseTime;
    }

    public void setPingjiaUseTime(String pingjiaUseTime) {
        this.pingjiaUseTime = pingjiaUseTime;
    }

    public String getPingjiaBeiyong1() {
        return this.pingjiaBeiyong1;
    }

    public void setPingjiaBeiyong1(String pingjiaBeiyong1) {
        this.pingjiaBeiyong1 = pingjiaBeiyong1;
    }

    public String getPingjiaBeiyong2() {
        return this.pingjiaBeiyong2;
    }

    public void setPingjiaBeiyong2(String pingjiaBeiyong2) {
        this.pingjiaBeiyong2 = pingjiaBeiyong2;
    }

    public String getPingjiaBeiyong3() {
        return this.pingjiaBeiyong3;
    }

    public void setPingjiaBeiyong3(String pingjiaBeiyong3) {
        this.pingjiaBeiyong3 = pingjiaBeiyong3;
    }

    public String getPingjiaBeiyong4() {
        return this.pingjiaBeiyong4;
    }

    public void setPingjiaBeiyong4(String pingjiaBeiyong4) {
        this.pingjiaBeiyong4 = pingjiaBeiyong4;
    }

	public List<CxwyMallComment> getCommentList() {
		return commentList;
	}


	public void setCommentList(List<CxwyMallComment> commentList) {
		this.commentList = commentList;
	}


	public int gettotal() {
		return total;
	}


	public void settotal(int total) {
		this.total = total;
	}

	public int getcollection() {
		return collection;
	}


	public void setcollection(int collection) {
		this.collection = collection;
	}

	@Override
	public String toString() {
		return "CxwyMallComment [commentList=" + commentList + ", total=" + total
				+ ", collection=" + collection + ", pingjiaId=" + pingjiaId
				+ ", pingjiaLevel=" + pingjiaLevel + ", pingjiaShangpNum="
				+ pingjiaShangpNum + ", pingjiaBody=" + pingjiaBody
				+ ", pingjiaName=" + pingjiaName + ", pingjiaImgSrc1="
				+ pingjiaImgSrc1 + ", pingjiaImgSrc2=" + pingjiaImgSrc2
				+ ", pingjiaImgSrc3=" + pingjiaImgSrc3 + ", pingjiaImgSrc4="
				+ pingjiaImgSrc4 + ", pingjiaNowTime=" + pingjiaNowTime
				+ ", pingjiaUseTime=" + pingjiaUseTime + ", pingjiaBeiyong1="
				+ pingjiaBeiyong1 + ", pingjiaBeiyong2=" + pingjiaBeiyong2
				+ ", pingjiaBeiyong3=" + pingjiaBeiyong3 + ", pingjiaBeiyong4="
				+ pingjiaBeiyong4 + "]";
	}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(commentList);
        dest.writeInt(total);
        dest.writeInt(collection);
        dest.writeString(pingjiaBody);
        dest.writeString(pingjiaName);
        dest.writeString(pingjiaImgSrc1);
        dest.writeString(pingjiaImgSrc2);
        dest.writeString(pingjiaImgSrc3);
        dest.writeString(pingjiaImgSrc4);
        dest.writeString(pingjiaNowTime);
        dest.writeString(pingjiaUseTime);
        dest.writeString(pingjiaBeiyong1);
        dest.writeString(pingjiaBeiyong2);
        dest.writeString(pingjiaBeiyong3);
        dest.writeString(pingjiaBeiyong4);
    }
}