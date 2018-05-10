package com.yxld.yxchuangxin.entity;

/**
 * @author wwx
 * @ClassName: SureOrderEntity
 * @Description:确认订单实体类Android
 * @date 2016年3月31日 上午10:17:54
 */
public class SureOrderEntity {

	private String goodsId;  //编号

	private String goodsNum;//数量

	private String cartId;//

	private String goodsRmb;//单价

	private String goodsSrc;//主图

	private String goodsShop;//商品名

	private String goodsDetails;//规格

	private String isDajianGoods; //-1 不是大件商品 1大件商品
	private String isYejian;

	public String getIsYejian() {
		return isYejian;
	}

	public void setIsYejian(String isYejian) {
		this.isYejian = isYejian;
	}

	public String getGoodsShop() {
		return goodsShop;
	}

	public void setGoodsShop(String goodsShop) {
		this.goodsShop = goodsShop;
	}

	public String getGoodsDetails() {
		return goodsDetails;
	}

	public void setGoodsDetails(String goodsDetails) {
		this.goodsDetails = goodsDetails;
	}

	public SureOrderEntity() {

	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getGoodsRmb() {
		return goodsRmb;
	}

	public void setGoodsRmb(String goodsRmb) {
		this.goodsRmb = goodsRmb;
	}

	public String getGoodsSrc() {
		return goodsSrc;
	}

	public void setGoodsSrc(String goodsSrc) {
		this.goodsSrc = goodsSrc;
	}

	public String getIsDajianGoods() {
		return isDajianGoods;
	}

	public void setIsDajianGoods(String isDajianGoods) {
		this.isDajianGoods = isDajianGoods;
	}

	public SureOrderEntity(String goodsId, String goodsNum, String cartId, String goodsSrc, String goodsRmb, String goodsShop, String goodsDetails, String isDajianGoods,String isYejian) {
		this.goodsId = goodsId;
		this.goodsNum = goodsNum;
		this.cartId = cartId;
		this.goodsSrc = goodsSrc;
		this.goodsRmb = goodsRmb;
		this.goodsShop = goodsShop;
		this.goodsDetails = goodsDetails;
		this.isDajianGoods = isDajianGoods;
		this.isYejian=isYejian;
	}

	@Override
	public String toString() {
		return "SureOrderEntity{" +
				"goodsId='" + goodsId + '\'' +
				", goodsNum='" + goodsNum + '\'' +
				", cartId='" + cartId + '\'' +
				", goodsRmb='" + goodsRmb + '\'' +
				", goodsSrc='" + goodsSrc + '\'' +
				", goodsShop='" + goodsShop + '\'' +
				", goodsDetails='" + goodsDetails + '\'' +
				", isDajianGoods=" + isDajianGoods +
				'}';
	}
}