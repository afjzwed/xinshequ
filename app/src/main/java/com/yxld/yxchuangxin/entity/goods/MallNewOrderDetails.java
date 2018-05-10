package com.yxld.yxchuangxin.entity.goods;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author xlei
 * @Date 2017/10/24.
 */

public class MallNewOrderDetails  implements Parcelable {
    private int id;		/*订单详情id*/
    private int xiangmuId;	/*项目id*/
    private int gongsiId;	/*公司id*/
    private String xiangmuMing;	/*项目名*/
    private String dingdanBianhao;	/*订单编号*/
    private int shangpinId;		/*商品id*/
    private String shangpinMing;	/*商品名*/
    private int shangpinShuliang;	/*商品数量*/
    private String shangpinGuige;	/*商品规格*/
    private double shangpinShoujia;		/*商品售价*/
    private double shangpinZongjia;		/*商品总价*/
    private double shangpinJinhuojia;	/*商品进货价*/
    private String suoluetu;	/*缩略图*/
    private String tiaoxingma;	/*条形码*/
    private String fenlei1;		/*一级分类*/
    private String fenlei2;		/*二级分类*/
    private int dingdanZhuangtai;	/*订单商品状态:1待支付、2待发货、3待收货、4待评价、5已完成、6退货中、7退款中、8已退款、9已取消、10退货完成*/
    private String tuikuanPicihao;	/*退款批次号（未确定该字段数据来源，先不处理）*/
    private int cartId;		/*购物车id 仅开发时使用（下单时根据购物车id删除购物车记录）*/
    // TODO: 2018/3/22  二维码支付 订单新加字段
    private int shuliang; //
    private String shangpinName;
    private double price;


    private boolean isChecked;
    private String comment;
    private double ratingNum;

    protected MallNewOrderDetails(Parcel in) {
        id = in.readInt();
        xiangmuId = in.readInt();
        gongsiId = in.readInt();
        xiangmuMing = in.readString();
        dingdanBianhao = in.readString();
        shangpinId = in.readInt();
        shangpinMing = in.readString();
        shangpinShuliang = in.readInt();
        shangpinGuige = in.readString();
        shangpinShoujia = in.readDouble();
        shangpinZongjia = in.readDouble();
        shangpinJinhuojia = in.readDouble();
        suoluetu = in.readString();
        tiaoxingma = in.readString();
        fenlei1 = in.readString();
        fenlei2 = in.readString();
        dingdanZhuangtai = in.readInt();
        tuikuanPicihao = in.readString();
        cartId = in.readInt();
        shuliang = in.readInt();
        shangpinName = in.readString();
        price = in.readDouble();
        isChecked = in.readByte() != 0;
        comment = in.readString();
        ratingNum = in.readDouble();
    }

    public static final Creator<MallNewOrderDetails> CREATOR = new Creator<MallNewOrderDetails>() {
        @Override
        public MallNewOrderDetails createFromParcel(Parcel in) {
            return new MallNewOrderDetails(in);
        }

        @Override
        public MallNewOrderDetails[] newArray(int size) {
            return new MallNewOrderDetails[size];
        }
    };

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRatingNum() {
        return ratingNum;
    }

    public void setRatingNum(double ratingNum) {
        this.ratingNum = ratingNum;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getXiangmuId() {
        return xiangmuId;
    }

    public void setXiangmuId(int xiangmuId) {
        this.xiangmuId = xiangmuId;
    }

    public int getGongsiId() {
        return gongsiId;
    }

    public void setGongsiId(int gongsiId) {
        this.gongsiId = gongsiId;
    }

    public String getXiangmuMing() {
        return xiangmuMing;
    }

    public void setXiangmuMing(String xiangmuMing) {
        this.xiangmuMing = xiangmuMing;
    }

    public String getDingdanBianhao() {
        return dingdanBianhao;
    }

    public void setDingdanBianhao(String dingdanBianhao) {
        this.dingdanBianhao = dingdanBianhao;
    }

    public int getShangpinId() {
        return shangpinId;
    }

    public void setShangpinId(int shangpinId) {
        this.shangpinId = shangpinId;
    }

    public String getShangpinMing() {
        return shangpinMing;
    }

    public void setShangpinMing(String shangpinMing) {
        this.shangpinMing = shangpinMing;
    }

    public int getShangpinShuliang() {
        return shangpinShuliang;
    }

    public void setShangpinShuliang(int shangpinShuliang) {
        this.shangpinShuliang = shangpinShuliang;
    }

    public String getShangpinGuige() {
        return shangpinGuige;
    }

    public void setShangpinGuige(String shangpinGuige) {
        this.shangpinGuige = shangpinGuige;
    }

    public double getShangpinShoujia() {
        return shangpinShoujia;
    }

    public void setShangpinShoujia(double shangpinShoujia) {
        this.shangpinShoujia = shangpinShoujia;
    }

    public double getShangpinZongjia() {
        return shangpinZongjia;
    }

    public void setShangpinZongjia(double shangpinZongjia) {
        this.shangpinZongjia = shangpinZongjia;
    }

    public double getShangpinJinhuojia() {
        return shangpinJinhuojia;
    }

    public void setShangpinJinhuojia(double shangpinJinhuojia) {
        this.shangpinJinhuojia = shangpinJinhuojia;
    }

    public String getSuoluetu() {
        return suoluetu;
    }

    public void setSuoluetu(String suoluetu) {
        this.suoluetu = suoluetu;
    }

    public String getTiaoxingma() {
        return tiaoxingma;
    }

    public void setTiaoxingma(String tiaoxingma) {
        this.tiaoxingma = tiaoxingma;
    }

    public String getFenlei1() {
        return fenlei1;
    }

    public void setFenlei1(String fenlei1) {
        this.fenlei1 = fenlei1;
    }

    public String getFenlei2() {
        return fenlei2;
    }

    public void setFenlei2(String fenlei2) {
        this.fenlei2 = fenlei2;
    }

    public int getDingdanZhuangtai() {
        return dingdanZhuangtai;
    }

    public void setDingdanZhuangtai(int dingdanZhuangtai) {
        this.dingdanZhuangtai = dingdanZhuangtai;
    }

    public String getTuikuanPicihao() {
        return tuikuanPicihao;
    }

    public void setTuikuanPicihao(String tuikuanPicihao) {
        this.tuikuanPicihao = tuikuanPicihao;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getShuliang() {
        return shuliang;
    }

    public void setShuliang(int shuliang) {
        this.shuliang = shuliang;
    }

    public String getShangpinName() {
        return shangpinName;
    }

    public void setShangpinName(String shangpinName) {
        this.shangpinName = shangpinName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(xiangmuId);
        dest.writeInt(gongsiId);
        dest.writeString(xiangmuMing);
        dest.writeString(dingdanBianhao);
        dest.writeInt(shangpinId);
        dest.writeString(shangpinMing);
        dest.writeInt(shangpinShuliang);
        dest.writeString(shangpinGuige);
        dest.writeDouble(shangpinShoujia);
        dest.writeDouble(shangpinZongjia);
        dest.writeDouble(shangpinJinhuojia);
        dest.writeString(suoluetu);
        dest.writeString(tiaoxingma);
        dest.writeString(fenlei1);
        dest.writeString(fenlei2);
        dest.writeInt(dingdanZhuangtai);
        dest.writeString(tuikuanPicihao);
        dest.writeInt(cartId);
        dest.writeInt(shuliang);
        dest.writeString(shangpinName);
        dest.writeDouble(price);
        dest.writeByte((byte) (isChecked ? 1 : 0));
        dest.writeString(comment);
        dest.writeDouble(ratingNum);
    }
}
