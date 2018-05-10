package com.yxld.yxchuangxin.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CarItemVo 
 * @Description: 购物车实体类Android  
 * @author wwx
 * @date 2016年3月17日 下午3:45:24 
 *
 */
public class CarItemVo {
	@SuppressWarnings("unused")
	private String totalMoney;// 购物车的总价格
	@SuppressWarnings("unused")
	private int tatalGoods;// 购物车商品总数量(不同商品的总和)
	private List<CxwyMallCart> goodsList;// 商品集合(单件商品集合)
	private List<CxwyMallCart> submitGoodsList; // 提交给订单的集合

	public List<CxwyMallCart> getSubmitGoodsList() {
		submitGoodsList = new ArrayList<CxwyMallCart>();
		for (int x = 0; x < goodsList.size(); x++) {
			if (goodsList.get(x).isChecked()) {
				submitGoodsList.add(goodsList.get(x));
			}
		}
		return submitGoodsList;
	}

	public void setSubmitGoodsList(List<CxwyMallCart> submitGoodsList) {
		this.submitGoodsList = submitGoodsList;
	}

	public String getTotalMoney() {
		double tatalMoney = 0d;
		for (int x = 0; x < goodsList.size(); x++) {
			double price = 0d;
			int num = 0;
			if (goodsList.get(x).isChecked()) {
				price = Double.parseDouble(goodsList.get(x).getCartOneRmb()+"");
				num = Integer.parseInt(goodsList.get(x).getCartNum());
			}
			double oneTatalGoods = price * num;
			tatalMoney += oneTatalGoods;
		}
		String tatalMoneys = tatalMoney+"";
		tatalMoneys = tatalMoneys.substring(0,tatalMoneys.indexOf(".")+2);
		return tatalMoneys;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public int getTatalGoods() {
		int tatalGoods = 0;
		for (int x = 0; x < goodsList.size(); x++) {
			int num = 0;
			if (goodsList.get(x).isChecked()) {
				num = Integer.parseInt(goodsList.get(x).getCartNum());
			}
			tatalGoods += num;
		}
		return tatalGoods;
	}

	public void setTatalGoods(int tatalGoods) {
		this.tatalGoods = tatalGoods;
	}

	public List<CxwyMallCart> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<CxwyMallCart> goodsList) {
		this.goodsList = goodsList;
	}
}
