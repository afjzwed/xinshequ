package com.yxld.yxchuangxin.entity;

/**
 * CxwyMallClassify entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CxwyMallClassify implements java.io.Serializable {
	private Integer classifyId;//id
	private String classifyOne;//一级分类
	
	private String classifyTwo;//二级分类
	private Integer classifyId2;//二级id,该记录所属分类级别
	
	private String classifyImgSrc;//图片
	
	private boolean isCheck = false;
	
	
	/** default constructor */
	public CxwyMallClassify() {
	}
	public Integer getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}
	public String getClassifyOne() {
		return classifyOne;
	}

	public void setClassifyOne(String classifyOne) {
		this.classifyOne = classifyOne;
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
	public String getClassifyTwo() {
		return classifyTwo;
	}
	public void setClassifyTwo(String classifyTwo) {
		this.classifyTwo = classifyTwo;
	}
	public Integer getClassifyId2() {
		return classifyId2;
	}
	public void setClassifyId2(Integer classifyId2) {
		this.classifyId2 = classifyId2;
	}
	public String getClassifyImgSrc() {
		return classifyImgSrc;
	}
	public void setClassifyImgSrc(String classifyImgSrc) {
		this.classifyImgSrc = classifyImgSrc;
	}
	@Override
	public String toString() {
		return "CxwyMallClassify [classifyId=" + classifyId + ", classifyOne="
				+ classifyOne + ", classifyTwo=" + classifyTwo
				+ ", classifyId2=" + classifyId2 + ", classifyImgSrc="
				+ classifyImgSrc + ", isCheck=" + isCheck + "]";
	}
	
}