package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * @ClassName: FirstClassGoodsInfo 
 * @Description: 一级分类信息
 * @author wwx
 * @date 2016年3月14日 下午12:07:37 
 *
 */
public class FirstClassGoodsInfo extends BaseEntity {

	/** 一级分类集合*/
	private List<CxwyMallClassify> classifyOne;

	public List<CxwyMallClassify> getClassifyOne() {
		return classifyOne;
	}

	public void setClassifyOne(List<CxwyMallClassify> classifyOne) {
		this.classifyOne = classifyOne;
	}

	@Override
	public String toString() {
		return "FirstClassGoodsInfo [classifyOne=" + classifyOne + ", status="
				+ status + ", MSG=" + MSG + "]";
	}
}
