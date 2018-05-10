package com.yxld.yxchuangxin.entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;


/**
 * CxwyMallCart entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CxwyMallCart  extends BaseEntity implements Parcelable {

	private List<CxwyMallCart> Cart;
	
    // Fields    
     private Integer cartId;//id
     private String cartShangpNum;//商品id
     private String cartShangpName;//商品名称
     private String cartSpec;//商品规格
     private String cartOneRmb = "0";//商品单价
     private String cartImgSrc;//图片路径
     private String cartNum;//购物车数量
     private String cartSpare1;//用户电话号码
     private String cartSpare2;//是否是大件商品 0  不是大件商品 1大件商品
     private String cartSpare3;
     private Integer cartShixiao;

    public Integer getCartShixiao() {
        return cartShixiao;
    }

    public void setCartShixiao(Integer cartShixiao) {
        this.cartShixiao = cartShixiao;
    }

    private boolean isChecked;

    /** default constructor */
    public CxwyMallCart() {
    }
    
    public CxwyMallCart(String cartShangpNum, String cartShangpName,
                        String cartSpec, String cartOneRmb, String cartImgSrc,
                        String cartNum) {
		super();
		this.cartShangpNum = cartShangpNum;
		this.cartShangpName = cartShangpName;
		this.cartSpec = cartSpec;
		this.cartOneRmb = cartOneRmb;
		this.cartImgSrc = cartImgSrc;
		this.cartNum = cartNum;
	}

	/** full constructor */
    public CxwyMallCart(String cartShangpNum, String cartShangpName, String cartSpec, String cartOneRmb, String cartImgSrc, String cartNum, String cartSpare1, String cartSpare2, String cartSpare3) {
        this.cartShangpNum = cartShangpNum;
        this.cartShangpName = cartShangpName;
        this.cartSpec = cartSpec;
        this.cartOneRmb = cartOneRmb;
        this.cartImgSrc = cartImgSrc;
        this.cartNum = cartNum;
        this.cartSpare1 = cartSpare1;
        this.cartSpare2 = cartSpare2;
        this.cartSpare3 = cartSpare3;
    }

    protected CxwyMallCart(Parcel in) {
        Cart = in.createTypedArrayList(CxwyMallCart.CREATOR);
        cartShangpNum = in.readString();
        cartShangpName = in.readString();
        cartSpec = in.readString();
        cartOneRmb = in.readString();
        cartImgSrc = in.readString();
        cartNum = in.readString();
        cartSpare1 = in.readString();
        cartSpare2 = in.readString();
        cartSpare3 = in.readString();
        isChecked = in.readByte() != 0;
    }

    public static final Creator<CxwyMallCart> CREATOR = new Creator<CxwyMallCart>() {
        @Override
        public CxwyMallCart createFromParcel(Parcel in) {
            return new CxwyMallCart(in);
        }

        @Override
        public CxwyMallCart[] newArray(int size) {
            return new CxwyMallCart[size];
        }
    };

    public List<CxwyMallCart> getCart() {
		return Cart;
	}

	public void setCart(List<CxwyMallCart> cart) {
		Cart = cart;
	}

	public Integer getCartId() {
        return this.cartId;
    }
    
    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getCartShangpNum() {
        return this.cartShangpNum;
    }
    
    public void setCartShangpNum(String cartShangpNum) {
        this.cartShangpNum = cartShangpNum;
    }

    public String getCartShangpName() {
        return this.cartShangpName;
    }
    
    public void setCartShangpName(String cartShangpName) {
        this.cartShangpName = cartShangpName;
    }

    public String getCartSpec() {
        return this.cartSpec;
    }
    
    public void setCartSpec(String cartSpec) {
        this.cartSpec = cartSpec;
    }

    public String getCartOneRmb() {
        return this.cartOneRmb;
    }
    
    public void setCartOneRmb(String cartOneRmb) {
        this.cartOneRmb = cartOneRmb;
    }

    public String getCartImgSrc() {
        return this.cartImgSrc;
    }
    
    public void setCartImgSrc(String cartImgSrc) {
        this.cartImgSrc = cartImgSrc;
    }

    public String getCartNum() {
        return this.cartNum;
    }
    
    public void setCartNum(String cartNum) {
        this.cartNum = cartNum;
    }

    public String getCartSpare1() {
        return this.cartSpare1;
    }
    
    public void setCartSpare1(String cartSpare1) {
        this.cartSpare1 = cartSpare1;
    }

    public String getCartSpare2() {
        return this.cartSpare2;
    }
    
    public void setCartSpare2(String cartSpare2) {
        this.cartSpare2 = cartSpare2;
    }

    public String getCartSpare3() {
        return this.cartSpare3;
    }
    
    public void setCartSpare3(String cartSpare3) {
        this.cartSpare3 = cartSpare3;
    }
    
	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	@Override
	public String toString() {
		return "CxwyMallCart [Cart=" + Cart + ", cartId=" + cartId
				+ ", cartShangpNum=" + cartShangpNum + ", cartShangpName="
				+ cartShangpName + ", cartSpec=" + cartSpec + ", cartOneRmb="
				+ cartOneRmb + ", cartImgSrc=" + cartImgSrc + ", cartNum="
				+ cartNum + ", cartSpare1=" + cartSpare1 + ", cartSpare2="
				+ cartSpare2 + ", cartSpare3=" + cartSpare3 + ", isChecked="
				+ isChecked + ", status=" + status + ", MSG=" + MSG + "]";
	}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(Cart);
        dest.writeString(cartShangpNum);
        dest.writeString(cartShangpName);
        dest.writeString(cartSpec);
        dest.writeString(cartOneRmb);
        dest.writeString(cartImgSrc);
        dest.writeString(cartNum);
        dest.writeString(cartSpare1);
        dest.writeString(cartSpare2);
        dest.writeString(cartSpare3);
        dest.writeByte((byte) (isChecked ? 1 : 0));
    }
}