package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * @ClassName: SecondClassGoodsInfo 
 * @Description: 二级分类实体类
 * @author wwx
 * @date 2016年3月14日 下午3:31:17 
 */
public class SecondClassGoodsInfo extends BaseEntity {

	private List<CxwyMallClassify> classifyTwo;

	
	public List<CxwyMallClassify> getClassifyTwo() {
		return classifyTwo;
	}


	public void setClassifyTwo(List<CxwyMallClassify> classifyTwo) {
		this.classifyTwo = classifyTwo;
	}


	@Override
	public String toString() {
		return "SecondClassGoodsInfo [classifyTwo=" + classifyTwo + ", status="
				+ status + ", MSG=" + MSG + "]";
	}
	
}
