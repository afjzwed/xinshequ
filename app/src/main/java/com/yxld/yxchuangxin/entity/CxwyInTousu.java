package com.yxld.yxchuangxin.entity;



/**
 * CxwyInTousu entity. @author MyEclipse Persistence Tools
 */

public class CxwyInTousu implements java.io.Serializable {

	// Fields
    //id
	private Integer tousuId;
	//投诉人
	private String tousuName;
	//投诉项目
	private String tousuXiangmu;
	//投诉类别
	 private String tousuLeibie;
	//投诉部门
	private String tousuDepater;
	//投诉职位
	private String tousuJob;
	//投诉人电话
	private String tousuPhone;
	//联系方式
	private String tousuDianhua;
	 private String tousuLoudong;
	 private String tousuDanyuan;
	 private String tousuFanghao;
	//投诉时间
	private String tousuShijian;
	//投诉内容
	private String tousuNeirong;
	//投诉状态
	private String tousuStatus;
	//解决方案
	private String tousuJiejuefangan;
	//解决时间
	private String tousuJiejuetime;
	//意见
	private String tousiSuggestion;
	//回执
	private String tousuHuizhi;
	//单号
	private String tousuDanhao;
	//总经理审批
	private String tousuZjlshenpi;
	//总经理签字
	private String tousuZjlqianming;
	//是否开会讨论
	private String tousuKaihuitaolun;
	//讨论结果
	private String tousuTaolunjieguo;
	//部门意见
	private String tousuBumenyijian;
	//部门签字
	private String tousuBumenqianzi;
	//备注1
	private String tousuBiezhu1;
	//备注2  
	private String tousuBiezhu2;

	// Constructors

	/** default constructor */
	public CxwyInTousu() {
	}

	/** full constructor */
	public CxwyInTousu(String tousuName, String tousuXiangmu, String tousuDepater, String tousuJob,
                       String tousuPhone, String tousuShijian, String tousuNeirong,
                       String tousuStatus, String tousuJiejuefangan,
                       String tousuJiejuetime, String tousiSuggestion,
                       String tousuHuizhi, String tousuDanhao, String tousuZjlshenpi,
                       String tousuZjlqianming, String tousuKaihuitaolun,
                       String tousuLoudong, String tousuDanyuan, String tousuFanghao,
                       String tousuTaolunjieguo, String tousuBumenyijian, String tousuLeibie,
                       String tousuBumenqianzi, String tousuBiezhu1, String tousuBiezhu2) {
		this.tousuName = tousuName;
		this.tousuDepater = tousuDepater;
		this.tousuXiangmu=tousuXiangmu;
		this.tousuJob = tousuJob;
		this.tousuPhone = tousuPhone;
		this.tousuShijian = tousuShijian;
		this.tousuNeirong = tousuNeirong;
		this.tousuStatus = tousuStatus;
		this.tousuJiejuefangan = tousuJiejuefangan;
		this.tousuJiejuetime = tousuJiejuetime;
		this.tousiSuggestion = tousiSuggestion;
		this.tousuHuizhi = tousuHuizhi;
		this.tousuDanhao = tousuDanhao;
		this.tousuLoudong=tousuLoudong;
		this.tousuDanyuan=tousuDanyuan;
		this.tousuFanghao=tousuFanghao;
		this.tousuLeibie=tousuLeibie;
		this.tousuZjlshenpi = tousuZjlshenpi;
		this.tousuZjlqianming = tousuZjlqianming;
		this.tousuKaihuitaolun = tousuKaihuitaolun;
		this.tousuTaolunjieguo = tousuTaolunjieguo;
		this.tousuBumenyijian = tousuBumenyijian;
		this.tousuBumenqianzi = tousuBumenqianzi;
		this.tousuBiezhu1 = tousuBiezhu1;
		this.tousuBiezhu2 = tousuBiezhu2;
	}

	// Property accessors
     
	
	
	public Integer getTousuId() {
		return this.tousuId;
	}

	public String getTousuDianhua() {
		return tousuDianhua;
	}

	public void setTousuDianhua(String tousuDianhua) {
		this.tousuDianhua = tousuDianhua;
	}

	

	public String getTousuLoudong() {
		return tousuLoudong;
	}

	public void setTousuLoudong(String tousuLoudong) {
		this.tousuLoudong = tousuLoudong;
	}

	public String getTousuDanyuan() {
		return tousuDanyuan;
	}

	public void setTousuDanyuan(String tousuDanyuan) {
		this.tousuDanyuan = tousuDanyuan;
	}

	public String getTousuFanghao() {
		return tousuFanghao;
	}

	public void setTousuFanghao(String tousuFanghao) {
		this.tousuFanghao = tousuFanghao;
	}

	public String getTousuXiangmu() {
		return tousuXiangmu;
	}

	public void setTousuXiangmu(String tousuXiangmu) {
		this.tousuXiangmu = tousuXiangmu;
	}

	public void setTousuId(Integer tousuId) {
		this.tousuId = tousuId;
	}

	public String getTousuName() {
		return this.tousuName;
	}

	public void setTousuName(String tousuName) {
		this.tousuName = tousuName;
	}

	public String getTousuDepater() {
		return this.tousuDepater;
	}

	public void setTousuDepater(String tousuDepater) {
		this.tousuDepater = tousuDepater;
	}

	public String getTousuJob() {
		return this.tousuJob;
	}

	public void setTousuJob(String tousuJob) {
		this.tousuJob = tousuJob;
	}



	public String getTousuPhone() {
		return tousuPhone;
	}

	public void setTousuPhone(String tousuPhone) {
		this.tousuPhone = tousuPhone;
	}

	public String getTousuShijian() {
		return this.tousuShijian;
	}

	public void setTousuShijian(String tousuShijian) {
		this.tousuShijian = tousuShijian;
	}

	public String getTousuNeirong() {
		return this.tousuNeirong;
	}

	public void setTousuNeirong(String tousuNeirong) {
		this.tousuNeirong = tousuNeirong;
	}

	public String getTousuStatus() {
		return this.tousuStatus;
	}

	public void setTousuStatus(String tousuStatus) {
		this.tousuStatus = tousuStatus;
	}

	public String getTousuJiejuefangan() {
		return this.tousuJiejuefangan;
	}

	public void setTousuJiejuefangan(String tousuJiejuefangan) {
		this.tousuJiejuefangan = tousuJiejuefangan;
	}

	public String getTousuJiejuetime() {
		return this.tousuJiejuetime;
	}

	public void setTousuJiejuetime(String tousuJiejuetime) {
		this.tousuJiejuetime = tousuJiejuetime;
	}

	public String getTousiSuggestion() {
		return this.tousiSuggestion;
	}

	public void setTousiSuggestion(String tousiSuggestion) {
		this.tousiSuggestion = tousiSuggestion;
	}

	public String getTousuHuizhi() {
		return this.tousuHuizhi;
	}

	public void setTousuHuizhi(String tousuHuizhi) {
		this.tousuHuizhi = tousuHuizhi;
	}

	public String getTousuDanhao() {
		return this.tousuDanhao;
	}

	public void setTousuDanhao(String tousuDanhao) {
		this.tousuDanhao = tousuDanhao;
	}

	public String getTousuZjlshenpi() {
		return this.tousuZjlshenpi;
	}

	public void setTousuZjlshenpi(String tousuZjlshenpi) {
		this.tousuZjlshenpi = tousuZjlshenpi;
	}

	public String getTousuZjlqianming() {
		return this.tousuZjlqianming;
	}

	public void setTousuZjlqianming(String tousuZjlqianming) {
		this.tousuZjlqianming = tousuZjlqianming;
	}

	public String getTousuKaihuitaolun() {
		return this.tousuKaihuitaolun;
	}

	public void setTousuKaihuitaolun(String tousuKaihuitaolun) {
		this.tousuKaihuitaolun = tousuKaihuitaolun;
	}

	public String getTousuTaolunjieguo() {
		return this.tousuTaolunjieguo;
	}

	public void setTousuTaolunjieguo(String tousuTaolunjieguo) {
		this.tousuTaolunjieguo = tousuTaolunjieguo;
	}

	public String getTousuBumenyijian() {
		return this.tousuBumenyijian;
	}

	public void setTousuBumenyijian(String tousuBumenyijian) {
		this.tousuBumenyijian = tousuBumenyijian;
	}

	public String getTousuBumenqianzi() {
		return this.tousuBumenqianzi;
	}

	public void setTousuBumenqianzi(String tousuBumenqianzi) {
		this.tousuBumenqianzi = tousuBumenqianzi;
	}

	public String getTousuBiezhu1() {
		return this.tousuBiezhu1;
	}

	public void setTousuBiezhu1(String tousuBiezhu1) {
		this.tousuBiezhu1 = tousuBiezhu1;
	}

	public String getTousuBiezhu2() {
		return this.tousuBiezhu2;
	}

	public void setTousuBiezhu2(String tousuBiezhu2) {
		this.tousuBiezhu2 = tousuBiezhu2;
	}

	public String getTousuLeibie() {
		return tousuLeibie;
	}

	public void setTousuLeibie(String tousuLeibie) {
		this.tousuLeibie = tousuLeibie;
	}

}