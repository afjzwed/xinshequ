package com.yxld.yxchuangxin.entity;

/**
 * CxwyOutBaoxiu entity. @author MyEclipse Persistence Tools
 */

public class CxwyOutBaoxiu implements java.io.Serializable {

	// Fields
     //id
	private Integer baoxiuId;
	//报修单元
	private String baoxiuDanhao;
	//报修楼盘
	private String baoxiuLoupan;
	//报修 楼栋
	private String baoxiuLoudong;
	//报修单元
	private String baoxiuDanyuan;
	//报修房号
	private String baoxiuFanghao;
	//报修姓名
	private String baoxiuName;
	//报修人电话
	private String baoxiuPhone;
	//报修 项目
	private String baoxiuXiangmu;
	//项目描述
	private String baoxiuXiangximiaoshu;
	//录入时间
	private String baoxiuLurutime;
	//结束时间
	private String baoxiuEndtime;
	//报修状态
	private String baoxiuStatus;
	//报修受理理人
	private String baoxiuClr;
	//报修经办人
	private String baoxiuJbr;
	//报修钱
	private Float baoxiuMoney;
	//材料费
	private Float baoxiuCailiaofei;
	 //编号 
	private String baoxiuBianhao;
	//回执方式
	private String baoxiuHuizhi;
	//签字
	private String baoxiuQianzi;
	//意见
	private String baoxiuYijian;
	//维修结果
	private String baoxiuWeixiujieguo;
	//备注（是否能在线解决）
	private String baoxiuBiezhu1;

	// Constructors

	/** default constructor */
	public CxwyOutBaoxiu() {
	}

	/** full constructor */
	public CxwyOutBaoxiu(String baoxiuDanhao, String baoxiuLoupan,
                         String baoxiuLoudong, String baoxiuDanyuan, String baoxiuFanghao,
                         String baoxiuName, String baoxiuPhone, String baoxiuXiangmu,
                         String baoxiuXiangximiaoshu, String baoxiuLurutime,
                         String baoxiuEndtime, String baoxiuStatus, String baoxiuClr,
                         String baoxiuJbr, Float baoxiuMoney, Float baoxiuCailiaofei,
                         String baoxiuBianhao, String baoxiuHuizhi, String baoxiuQianzi,
                         String baoxiuYijian, String baoxiuWeixiujieguo, String baoxiuBiezhu1) {
		this.baoxiuDanhao = baoxiuDanhao;
		this.baoxiuLoupan = baoxiuLoupan;
		this.baoxiuLoudong = baoxiuLoudong;
		this.baoxiuDanyuan = baoxiuDanyuan;
		this.baoxiuFanghao = baoxiuFanghao;
		this.baoxiuName = baoxiuName;
		this.baoxiuPhone = baoxiuPhone;
		this.baoxiuXiangmu = baoxiuXiangmu;
		this.baoxiuXiangximiaoshu = baoxiuXiangximiaoshu;
		this.baoxiuLurutime = baoxiuLurutime;
		this.baoxiuEndtime = baoxiuEndtime;
		this.baoxiuStatus = baoxiuStatus;
		this.baoxiuClr = baoxiuClr;
		this.baoxiuJbr = baoxiuJbr;
		this.baoxiuMoney = baoxiuMoney;
		this.baoxiuCailiaofei = baoxiuCailiaofei;
		this.baoxiuBianhao = baoxiuBianhao;
		this.baoxiuHuizhi = baoxiuHuizhi;
		this.baoxiuQianzi = baoxiuQianzi;
		this.baoxiuYijian = baoxiuYijian;
		this.baoxiuWeixiujieguo = baoxiuWeixiujieguo;
		this.baoxiuBiezhu1 = baoxiuBiezhu1;
	}

	// Property accessors

	public Integer getBaoxiuId() {
		return this.baoxiuId;
	}

	public void setBaoxiuId(Integer baoxiuId) {
		this.baoxiuId = baoxiuId;
	}

	public String getBaoxiuDanhao() {
		return this.baoxiuDanhao;
	}

	public void setBaoxiuDanhao(String baoxiuDanhao) {
		this.baoxiuDanhao = baoxiuDanhao;
	}

	public String getBaoxiuLoupan() {
		return this.baoxiuLoupan;
	}

	public void setBaoxiuLoupan(String baoxiuLoupan) {
		this.baoxiuLoupan = baoxiuLoupan;
	}

	public String getBaoxiuLoudong() {
		return this.baoxiuLoudong;
	}

	public void setBaoxiuLoudong(String baoxiuLoudong) {
		this.baoxiuLoudong = baoxiuLoudong;
	}

	public String getBaoxiuDanyuan() {
		return this.baoxiuDanyuan;
	}

	public void setBaoxiuDanyuan(String baoxiuDanyuan) {
		this.baoxiuDanyuan = baoxiuDanyuan;
	}

	public String getBaoxiuFanghao() {
		return this.baoxiuFanghao;
	}

	public void setBaoxiuFanghao(String baoxiuFanghao) {
		this.baoxiuFanghao = baoxiuFanghao;
	}

	public String getBaoxiuName() {
		return this.baoxiuName;
	}

	public void setBaoxiuName(String baoxiuName) {
		this.baoxiuName = baoxiuName;
	}

	
	public String getBaoxiuPhone() {
		return baoxiuPhone;
	}

	public void setBaoxiuPhone(String baoxiuPhone) {
		this.baoxiuPhone = baoxiuPhone;
	}

	public String getBaoxiuXiangmu() {
		return this.baoxiuXiangmu;
	}

	public void setBaoxiuXiangmu(String baoxiuXiangmu) {
		this.baoxiuXiangmu = baoxiuXiangmu;
	}

	public String getBaoxiuXiangximiaoshu() {
		return this.baoxiuXiangximiaoshu;
	}

	public void setBaoxiuXiangximiaoshu(String baoxiuXiangximiaoshu) {
		this.baoxiuXiangximiaoshu = baoxiuXiangximiaoshu;
	}

	

	public String getBaoxiuLurutime() {
		return baoxiuLurutime;
	}

	public void setBaoxiuLurutime(String baoxiuLurutime) {
		this.baoxiuLurutime = baoxiuLurutime;
	}

	public String getBaoxiuEndtime() {
		return baoxiuEndtime;
	}

	public void setBaoxiuEndtime(String baoxiuEndtime) {
		this.baoxiuEndtime = baoxiuEndtime;
	}

	public String getBaoxiuStatus() {
		return this.baoxiuStatus;
	}

	public void setBaoxiuStatus(String baoxiuStatus) {
		this.baoxiuStatus = baoxiuStatus;
	}

	public String getBaoxiuClr() {
		return this.baoxiuClr;
	}

	public void setBaoxiuClr(String baoxiuClr) {
		this.baoxiuClr = baoxiuClr;
	}

	public String getBaoxiuJbr() {
		return this.baoxiuJbr;
	}

	public void setBaoxiuJbr(String baoxiuJbr) {
		this.baoxiuJbr = baoxiuJbr;
	}

	public Float getBaoxiuMoney() {
		return this.baoxiuMoney;
	}

	public void setBaoxiuMoney(Float baoxiuMoney) {
		this.baoxiuMoney = baoxiuMoney;
	}

	public Float getBaoxiuCailiaofei() {
		return this.baoxiuCailiaofei;
	}

	public void setBaoxiuCailiaofei(Float baoxiuCailiaofei) {
		this.baoxiuCailiaofei = baoxiuCailiaofei;
	}

	public String getBaoxiuBianhao() {
		return this.baoxiuBianhao;
	}

	public void setBaoxiuBianhao(String baoxiuBianhao) {
		this.baoxiuBianhao = baoxiuBianhao;
	}

	public String getBaoxiuHuizhi() {
		return this.baoxiuHuizhi;
	}

	public void setBaoxiuHuizhi(String baoxiuHuizhi) {
		this.baoxiuHuizhi = baoxiuHuizhi;
	}

	public String getBaoxiuQianzi() {
		return this.baoxiuQianzi;
	}

	public void setBaoxiuQianzi(String baoxiuQianzi) {
		this.baoxiuQianzi = baoxiuQianzi;
	}

	public String getBaoxiuYijian() {
		return this.baoxiuYijian;
	}

	public void setBaoxiuYijian(String baoxiuYijian) {
		this.baoxiuYijian = baoxiuYijian;
	}

	public String getBaoxiuWeixiujieguo() {
		return this.baoxiuWeixiujieguo;
	}

	public void setBaoxiuWeixiujieguo(String baoxiuWeixiujieguo) {
		this.baoxiuWeixiujieguo = baoxiuWeixiujieguo;
	}

	public String getBaoxiuBiezhu1() {
		return this.baoxiuBiezhu1;
	}

	public void setBaoxiuBiezhu1(String baoxiuBiezhu1) {
		this.baoxiuBiezhu1 = baoxiuBiezhu1;
	}

}