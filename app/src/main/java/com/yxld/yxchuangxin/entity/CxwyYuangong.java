package com.yxld.yxchuangxin.entity;

/**
 * CxwyYuangong entity. @author MyEclipse Persistence Tools
 */

public class CxwyYuangong implements java.io.Serializable {

	// Fields

	private Integer yuangongId;
	private String yuangongBx3; // 员工楼盘
	private String yuangongBumen;// 员工部门
	private String yuangongZhiwu;// 员工职务
	private String yuangongName; // 员工的姓名
	private String yuangongSex; // 员工性别
	private String yuangongIdcard;// 身份证
	private String yuangongBx4;// 员工手机
	private String yuangongGongzi4;// 员工联系方式1
	private String yuangongGongzi6;// 员工联系方式2
	private String yuangongBx1;// 员工入职日期
	private String yuangongMima;// 一级密码
	private String yuangongMima2;// 二次确认密码

	// 暂时没有用到的字段
	private String yuangongShangsi;
	private String yuangongGongzi;
	private String yuangongBaoxiaoshenqiingriqi;
	private String yuangongBaoxiaojine;
	private String yuangongBaoxiaoyuanyin;
	private String yuangongBaoxiaopingzheng;
	private String yuangongXmfzrshzt;
	private String yuangongCwbzshzt;
	private String yuangongFgbshzt;
	private String yuangongZjlshzt;
	private String yuangongDszshzt;
	private String yuangongXmfzrqm;
	private String yuangongCwbzqm;
	private String yuangongFgbqm;
	private String yuangongZjlqm;
	private String yuangongDszqm;
	private String yuangongShifoubaoxiao;
	private String yuangongShifoutijiaopiaoju;
	private String yuangongBx2;
	private String yuangongBx5;
	private String yuangongJiekuanshenqiriqi;
	private String yuangongJiekuanjine;
	private String yuangongJiekuanliyou;
	private String yuangongJiekuanshenheyijian;
	private String yuangongJiekuanhuankuanriqi;
	private String yuangongJiekuanshifoujiechu;
	private String yuangongJiekuanshifouguihuan;
	private String yuangongJk1;
	private String yuangongJk2;
	private String yuangongJk3;
	private String yuangongJk4;
	private String yuangongJk5;
	private String yuangongJk6;
	private String yuangongJk7;
	private String yuangongJk8;
	private String yuangongGongzifanian;
	private String yuangongGongziyue;
	private String yuangongGongzifafangpizheng;
	private String yuangongGongzifafangshenpiyijian;
	private String yuangongGongzifafangpishiqm;
	private String yuangongGongzishifoufafang;
	private String yuangongGongzifafangzongshu;
	private String yuangongGongziA;
	private String yuangongGongzi2;
	private String yuangongGongzi3;

	// Constructors

	/** default constructor */
	public CxwyYuangong() {
	}

	/** full constructor */
	public CxwyYuangong(String yuangongName, String yuangongSex,
                        String yuangongIdcard, String yuangongBumen,
                        String yuangongShangsi, String yuangongZhiwu,
                        String yuangongGongzi, String yuangongMima, String yuangongMima2,
                        String yuangongBaoxiaoshenqiingriqi, String yuangongBaoxiaojine,
                        String yuangongBaoxiaoyuanyin, String yuangongBaoxiaopingzheng,
                        String yuangongXmfzrshzt, String yuangongCwbzshzt,
                        String yuangongFgbshzt, String yuangongZjlshzt,
                        String yuangongDszshzt, String yuangongXmfzrqm,
                        String yuangongCwbzqm, String yuangongFgbqm, String yuangongZjlqm,
                        String yuangongDszqm, String yuangongShifoubaoxiao,
                        String yuangongShifoutijiaopiaoju, String yuangongBx1,
                        String yuangongBx2, String yuangongBx3, String yuangongBx4,
                        String yuangongBx5, String yuangongJiekuanshenqiriqi,
                        String yuangongJiekuanjine, String yuangongJiekuanliyou,
                        String yuangongJiekuanshenheyijian,
                        String yuangongJiekuanhuankuanriqi,
                        String yuangongJiekuanshifoujiechu,
                        String yuangongJiekuanshifouguihuan, String yuangongJk1,
                        String yuangongJk2, String yuangongJk3, String yuangongJk4,
                        String yuangongJk5, String yuangongJk6, String yuangongJk7,
                        String yuangongJk8, String yuangongGongzifanian,
                        String yuangongGongziyue, String yuangongGongzifafangpizheng,
                        String yuangongGongzifafangshenpiyijian,
                        String yuangongGongzifafangpishiqm,
                        String yuangongGongzishifoufafang,
                        String yuangongGongzifafangzongshu, String yuangongGongziA,
                        String yuangongGongzi2, String yuangongGongzi3,
                        String yuangongGongzi4, String yuangongGongzi6) {
		this.yuangongName = yuangongName;
		this.yuangongSex = yuangongSex;
		this.yuangongIdcard = yuangongIdcard;
		this.yuangongBumen = yuangongBumen;
		this.yuangongShangsi = yuangongShangsi;
		this.yuangongZhiwu = yuangongZhiwu;
		this.yuangongGongzi = yuangongGongzi;
		this.yuangongMima = yuangongMima;
		this.yuangongMima2 = yuangongMima2;
		this.yuangongBaoxiaoshenqiingriqi = yuangongBaoxiaoshenqiingriqi;
		this.yuangongBaoxiaojine = yuangongBaoxiaojine;
		this.yuangongBaoxiaoyuanyin = yuangongBaoxiaoyuanyin;
		this.yuangongBaoxiaopingzheng = yuangongBaoxiaopingzheng;
		this.yuangongXmfzrshzt = yuangongXmfzrshzt;
		this.yuangongCwbzshzt = yuangongCwbzshzt;
		this.yuangongFgbshzt = yuangongFgbshzt;
		this.yuangongZjlshzt = yuangongZjlshzt;
		this.yuangongDszshzt = yuangongDszshzt;
		this.yuangongXmfzrqm = yuangongXmfzrqm;
		this.yuangongCwbzqm = yuangongCwbzqm;
		this.yuangongFgbqm = yuangongFgbqm;
		this.yuangongZjlqm = yuangongZjlqm;
		this.yuangongDszqm = yuangongDszqm;
		this.yuangongShifoubaoxiao = yuangongShifoubaoxiao;
		this.yuangongShifoutijiaopiaoju = yuangongShifoutijiaopiaoju;
		this.yuangongBx1 = yuangongBx1;
		this.yuangongBx2 = yuangongBx2;
		this.yuangongBx3 = yuangongBx3;
		this.yuangongBx4 = yuangongBx4;
		this.yuangongBx5 = yuangongBx5;
		this.yuangongJiekuanshenqiriqi = yuangongJiekuanshenqiriqi;
		this.yuangongJiekuanjine = yuangongJiekuanjine;
		this.yuangongJiekuanliyou = yuangongJiekuanliyou;
		this.yuangongJiekuanshenheyijian = yuangongJiekuanshenheyijian;
		this.yuangongJiekuanhuankuanriqi = yuangongJiekuanhuankuanriqi;
		this.yuangongJiekuanshifoujiechu = yuangongJiekuanshifoujiechu;
		this.yuangongJiekuanshifouguihuan = yuangongJiekuanshifouguihuan;
		this.yuangongJk1 = yuangongJk1;
		this.yuangongJk2 = yuangongJk2;
		this.yuangongJk3 = yuangongJk3;
		this.yuangongJk4 = yuangongJk4;
		this.yuangongJk5 = yuangongJk5;
		this.yuangongJk6 = yuangongJk6;
		this.yuangongJk7 = yuangongJk7;
		this.yuangongJk8 = yuangongJk8;
		this.yuangongGongzifanian = yuangongGongzifanian;
		this.yuangongGongziyue = yuangongGongziyue;
		this.yuangongGongzifafangpizheng = yuangongGongzifafangpizheng;
		this.yuangongGongzifafangshenpiyijian = yuangongGongzifafangshenpiyijian;
		this.yuangongGongzifafangpishiqm = yuangongGongzifafangpishiqm;
		this.yuangongGongzishifoufafang = yuangongGongzishifoufafang;
		this.yuangongGongzifafangzongshu = yuangongGongzifafangzongshu;
		this.yuangongGongziA = yuangongGongziA;
		this.yuangongGongzi2 = yuangongGongzi2;
		this.yuangongGongzi3 = yuangongGongzi3;
		this.yuangongGongzi4 = yuangongGongzi4;
		this.yuangongGongzi6 = yuangongGongzi6;
	}

	// Property accessors

	public Integer getYuangongId() {
		return this.yuangongId;
	}

	public void setYuangongId(Integer yuangongId) {
		this.yuangongId = yuangongId;
	}

	public String getYuangongName() {
		return this.yuangongName;
	}

	public void setYuangongName(String yuangongName) {
		this.yuangongName = yuangongName;
	}

	public String getYuangongSex() {
		return this.yuangongSex;
	}

	public void setYuangongSex(String yuangongSex) {
		this.yuangongSex = yuangongSex;
	}

	public String getYuangongIdcard() {
		return this.yuangongIdcard;
	}

	public void setYuangongIdcard(String yuangongIdcard) {
		this.yuangongIdcard = yuangongIdcard;
	}

	public String getYuangongBumen() {
		return this.yuangongBumen;
	}

	public void setYuangongBumen(String yuangongBumen) {
		this.yuangongBumen = yuangongBumen;
	}

	public String getYuangongShangsi() {
		return this.yuangongShangsi;
	}

	public void setYuangongShangsi(String yuangongShangsi) {
		this.yuangongShangsi = yuangongShangsi;
	}

	public String getYuangongZhiwu() {
		return this.yuangongZhiwu;
	}

	public void setYuangongZhiwu(String yuangongZhiwu) {
		this.yuangongZhiwu = yuangongZhiwu;
	}

	public String getYuangongGongzi() {
		return this.yuangongGongzi;
	}

	public void setYuangongGongzi(String yuangongGongzi) {
		this.yuangongGongzi = yuangongGongzi;
	}

	public String getYuangongMima() {
		return this.yuangongMima;
	}

	public void setYuangongMima(String yuangongMima) {
		this.yuangongMima = yuangongMima;
	}

	public String getYuangongMima2() {
		return this.yuangongMima2;
	}

	public void setYuangongMima2(String yuangongMima2) {
		this.yuangongMima2 = yuangongMima2;
	}

	public String getYuangongBaoxiaojine() {
		return this.yuangongBaoxiaojine;
	}

	public void setYuangongBaoxiaojine(String yuangongBaoxiaojine) {
		this.yuangongBaoxiaojine = yuangongBaoxiaojine;
	}

	public String getYuangongBaoxiaoyuanyin() {
		return this.yuangongBaoxiaoyuanyin;
	}

	public void setYuangongBaoxiaoyuanyin(String yuangongBaoxiaoyuanyin) {
		this.yuangongBaoxiaoyuanyin = yuangongBaoxiaoyuanyin;
	}

	public String getYuangongBaoxiaopingzheng() {
		return this.yuangongBaoxiaopingzheng;
	}

	public void setYuangongBaoxiaopingzheng(String yuangongBaoxiaopingzheng) {
		this.yuangongBaoxiaopingzheng = yuangongBaoxiaopingzheng;
	}

	public String getYuangongXmfzrshzt() {
		return this.yuangongXmfzrshzt;
	}

	public void setYuangongXmfzrshzt(String yuangongXmfzrshzt) {
		this.yuangongXmfzrshzt = yuangongXmfzrshzt;
	}

	public String getYuangongCwbzshzt() {
		return this.yuangongCwbzshzt;
	}

	public void setYuangongCwbzshzt(String yuangongCwbzshzt) {
		this.yuangongCwbzshzt = yuangongCwbzshzt;
	}

	public String getYuangongFgbshzt() {
		return this.yuangongFgbshzt;
	}

	public void setYuangongFgbshzt(String yuangongFgbshzt) {
		this.yuangongFgbshzt = yuangongFgbshzt;
	}

	public String getYuangongZjlshzt() {
		return this.yuangongZjlshzt;
	}

	public void setYuangongZjlshzt(String yuangongZjlshzt) {
		this.yuangongZjlshzt = yuangongZjlshzt;
	}

	public String getYuangongDszshzt() {
		return this.yuangongDszshzt;
	}

	public void setYuangongDszshzt(String yuangongDszshzt) {
		this.yuangongDszshzt = yuangongDszshzt;
	}

	public String getYuangongXmfzrqm() {
		return this.yuangongXmfzrqm;
	}

	public void setYuangongXmfzrqm(String yuangongXmfzrqm) {
		this.yuangongXmfzrqm = yuangongXmfzrqm;
	}

	public String getYuangongCwbzqm() {
		return this.yuangongCwbzqm;
	}

	public void setYuangongCwbzqm(String yuangongCwbzqm) {
		this.yuangongCwbzqm = yuangongCwbzqm;
	}

	public String getYuangongFgbqm() {
		return this.yuangongFgbqm;
	}

	public void setYuangongFgbqm(String yuangongFgbqm) {
		this.yuangongFgbqm = yuangongFgbqm;
	}

	public String getYuangongZjlqm() {
		return this.yuangongZjlqm;
	}

	public void setYuangongZjlqm(String yuangongZjlqm) {
		this.yuangongZjlqm = yuangongZjlqm;
	}

	public String getYuangongDszqm() {
		return this.yuangongDszqm;
	}

	public void setYuangongDszqm(String yuangongDszqm) {
		this.yuangongDszqm = yuangongDszqm;
	}

	public String getYuangongShifoubaoxiao() {
		return this.yuangongShifoubaoxiao;
	}

	public void setYuangongShifoubaoxiao(String yuangongShifoubaoxiao) {
		this.yuangongShifoubaoxiao = yuangongShifoubaoxiao;
	}

	public String getYuangongShifoutijiaopiaoju() {
		return this.yuangongShifoutijiaopiaoju;
	}

	public void setYuangongShifoutijiaopiaoju(String yuangongShifoutijiaopiaoju) {
		this.yuangongShifoutijiaopiaoju = yuangongShifoutijiaopiaoju;
	}

	public String getYuangongBx1() {
		return this.yuangongBx1;
	}

	public void setYuangongBx1(String yuangongBx1) {
		this.yuangongBx1 = yuangongBx1;
	}

	public String getYuangongBx2() {
		return this.yuangongBx2;
	}

	public void setYuangongBx2(String yuangongBx2) {
		this.yuangongBx2 = yuangongBx2;
	}

	public String getYuangongBx3() {
		return this.yuangongBx3;
	}

	public void setYuangongBx3(String yuangongBx3) {
		this.yuangongBx3 = yuangongBx3;
	}

	public String getYuangongBx4() {
		return this.yuangongBx4;
	}

	public void setYuangongBx4(String yuangongBx4) {
		this.yuangongBx4 = yuangongBx4;
	}

	public String getYuangongBx5() {
		return this.yuangongBx5;
	}

	public void setYuangongBx5(String yuangongBx5) {
		this.yuangongBx5 = yuangongBx5;
	}

	public String getYuangongJiekuanjine() {
		return this.yuangongJiekuanjine;
	}

	public void setYuangongJiekuanjine(String yuangongJiekuanjine) {
		this.yuangongJiekuanjine = yuangongJiekuanjine;
	}

	public String getYuangongJiekuanliyou() {
		return this.yuangongJiekuanliyou;
	}

	public void setYuangongJiekuanliyou(String yuangongJiekuanliyou) {
		this.yuangongJiekuanliyou = yuangongJiekuanliyou;
	}

	public String getYuangongJiekuanshenheyijian() {
		return this.yuangongJiekuanshenheyijian;
	}

	public void setYuangongJiekuanshenheyijian(
			String yuangongJiekuanshenheyijian) {
		this.yuangongJiekuanshenheyijian = yuangongJiekuanshenheyijian;
	}

	public String getYuangongJiekuanshifoujiechu() {
		return this.yuangongJiekuanshifoujiechu;
	}

	public void setYuangongJiekuanshifoujiechu(
			String yuangongJiekuanshifoujiechu) {
		this.yuangongJiekuanshifoujiechu = yuangongJiekuanshifoujiechu;
	}

	public String getYuangongJiekuanshifouguihuan() {
		return this.yuangongJiekuanshifouguihuan;
	}

	public void setYuangongJiekuanshifouguihuan(
			String yuangongJiekuanshifouguihuan) {
		this.yuangongJiekuanshifouguihuan = yuangongJiekuanshifouguihuan;
	}

	public String getYuangongJk1() {
		return this.yuangongJk1;
	}

	public void setYuangongJk1(String yuangongJk1) {
		this.yuangongJk1 = yuangongJk1;
	}

	public String getYuangongJk2() {
		return this.yuangongJk2;
	}

	public void setYuangongJk2(String yuangongJk2) {
		this.yuangongJk2 = yuangongJk2;
	}

	public String getYuangongJk3() {
		return this.yuangongJk3;
	}

	public void setYuangongJk3(String yuangongJk3) {
		this.yuangongJk3 = yuangongJk3;
	}

	public String getYuangongJk4() {
		return this.yuangongJk4;
	}

	public void setYuangongJk4(String yuangongJk4) {
		this.yuangongJk4 = yuangongJk4;
	}

	public String getYuangongJk5() {
		return this.yuangongJk5;
	}

	public void setYuangongJk5(String yuangongJk5) {
		this.yuangongJk5 = yuangongJk5;
	}

	public String getYuangongJk6() {
		return this.yuangongJk6;
	}

	public void setYuangongJk6(String yuangongJk6) {
		this.yuangongJk6 = yuangongJk6;
	}

	public String getYuangongJk7() {
		return this.yuangongJk7;
	}

	public void setYuangongJk7(String yuangongJk7) {
		this.yuangongJk7 = yuangongJk7;
	}

	public String getYuangongJk8() {
		return this.yuangongJk8;
	}

	public void setYuangongJk8(String yuangongJk8) {
		this.yuangongJk8 = yuangongJk8;
	}

	public String getYuangongGongzifanian() {
		return this.yuangongGongzifanian;
	}

	public void setYuangongGongzifanian(String yuangongGongzifanian) {
		this.yuangongGongzifanian = yuangongGongzifanian;
	}

	public String getYuangongGongziyue() {
		return this.yuangongGongziyue;
	}

	public void setYuangongGongziyue(String yuangongGongziyue) {
		this.yuangongGongziyue = yuangongGongziyue;
	}

	public String getYuangongGongzifafangpizheng() {
		return this.yuangongGongzifafangpizheng;
	}

	public void setYuangongGongzifafangpizheng(
			String yuangongGongzifafangpizheng) {
		this.yuangongGongzifafangpizheng = yuangongGongzifafangpizheng;
	}

	public String getYuangongGongzifafangshenpiyijian() {
		return this.yuangongGongzifafangshenpiyijian;
	}

	public void setYuangongGongzifafangshenpiyijian(
			String yuangongGongzifafangshenpiyijian) {
		this.yuangongGongzifafangshenpiyijian = yuangongGongzifafangshenpiyijian;
	}

	public String getYuangongGongzifafangpishiqm() {
		return this.yuangongGongzifafangpishiqm;
	}

	public void setYuangongGongzifafangpishiqm(
			String yuangongGongzifafangpishiqm) {
		this.yuangongGongzifafangpishiqm = yuangongGongzifafangpishiqm;
	}

	public String getYuangongGongzishifoufafang() {
		return this.yuangongGongzishifoufafang;
	}

	public void setYuangongGongzishifoufafang(String yuangongGongzishifoufafang) {
		this.yuangongGongzishifoufafang = yuangongGongzishifoufafang;
	}

	public String getYuangongGongzifafangzongshu() {
		return this.yuangongGongzifafangzongshu;
	}

	public void setYuangongGongzifafangzongshu(
			String yuangongGongzifafangzongshu) {
		this.yuangongGongzifafangzongshu = yuangongGongzifafangzongshu;
	}

	public String getYuangongGongziA() {
		return this.yuangongGongziA;
	}

	public void setYuangongGongziA(String yuangongGongziA) {
		this.yuangongGongziA = yuangongGongziA;
	}

	public String getYuangongGongzi2() {
		return this.yuangongGongzi2;
	}

	public void setYuangongGongzi2(String yuangongGongzi2) {
		this.yuangongGongzi2 = yuangongGongzi2;
	}

	public String getYuangongGongzi3() {
		return this.yuangongGongzi3;
	}

	public void setYuangongGongzi3(String yuangongGongzi3) {
		this.yuangongGongzi3 = yuangongGongzi3;
	}

	public String getYuangongGongzi4() {
		return this.yuangongGongzi4;
	}

	public void setYuangongGongzi4(String yuangongGongzi4) {
		this.yuangongGongzi4 = yuangongGongzi4;
	}

	public String getYuangongGongzi6() {
		return this.yuangongGongzi6;
	}

	public void setYuangongGongzi6(String yuangongGongzi6) {
		this.yuangongGongzi6 = yuangongGongzi6;
	}

	public String getYuangongBaoxiaoshenqiingriqi() {
		return yuangongBaoxiaoshenqiingriqi;
	}

	public void setYuangongBaoxiaoshenqiingriqi(
			String yuangongBaoxiaoshenqiingriqi) {
		this.yuangongBaoxiaoshenqiingriqi = yuangongBaoxiaoshenqiingriqi;
	}

	public String getYuangongJiekuanshenqiriqi() {
		return yuangongJiekuanshenqiriqi;
	}

	public void setYuangongJiekuanshenqiriqi(String yuangongJiekuanshenqiriqi) {
		this.yuangongJiekuanshenqiriqi = yuangongJiekuanshenqiriqi;
	}

	public String getYuangongJiekuanhuankuanriqi() {
		return yuangongJiekuanhuankuanriqi;
	}

	public void setYuangongJiekuanhuankuanriqi(
			String yuangongJiekuanhuankuanriqi) {
		this.yuangongJiekuanhuankuanriqi = yuangongJiekuanhuankuanriqi;
	}

	@Override
	public String toString() {
		return "CxwyYuangong [yuangongId=" + yuangongId + ", yuangongBx3="
				+ yuangongBx3 + ", yuangongBumen=" + yuangongBumen
				+ ", yuangongZhiwu=" + yuangongZhiwu + ", yuangongName="
				+ yuangongName + ", yuangongSex=" + yuangongSex
				+ ", yuangongIdcard=" + yuangongIdcard + ", yuangongBx4="
				+ yuangongBx4 + ", yuangongGongzi4=" + yuangongGongzi4
				+ ", yuangongGongzi6=" + yuangongGongzi6 + ", yuangongBx1="
				+ yuangongBx1 + ", yuangongMima=" + yuangongMima
				+ ", yuangongMima2=" + yuangongMima2 + ", yuangongShangsi="
				+ yuangongShangsi + ", yuangongGongzi=" + yuangongGongzi
				+ ", yuangongBaoxiaoshenqiingriqi="
				+ yuangongBaoxiaoshenqiingriqi + ", yuangongBaoxiaojine="
				+ yuangongBaoxiaojine + ", yuangongBaoxiaoyuanyin="
				+ yuangongBaoxiaoyuanyin + ", yuangongBaoxiaopingzheng="
				+ yuangongBaoxiaopingzheng + ", yuangongXmfzrshzt="
				+ yuangongXmfzrshzt + ", yuangongCwbzshzt=" + yuangongCwbzshzt
				+ ", yuangongFgbshzt=" + yuangongFgbshzt + ", yuangongZjlshzt="
				+ yuangongZjlshzt + ", yuangongDszshzt=" + yuangongDszshzt
				+ ", yuangongXmfzrqm=" + yuangongXmfzrqm + ", yuangongCwbzqm="
				+ yuangongCwbzqm + ", yuangongFgbqm=" + yuangongFgbqm
				+ ", yuangongZjlqm=" + yuangongZjlqm + ", yuangongDszqm="
				+ yuangongDszqm + ", yuangongShifoubaoxiao="
				+ yuangongShifoubaoxiao + ", yuangongShifoutijiaopiaoju="
				+ yuangongShifoutijiaopiaoju + ", yuangongBx2=" + yuangongBx2
				+ ", yuangongBx5=" + yuangongBx5
				+ ", yuangongJiekuanshenqiriqi=" + yuangongJiekuanshenqiriqi
				+ ", yuangongJiekuanjine=" + yuangongJiekuanjine
				+ ", yuangongJiekuanliyou=" + yuangongJiekuanliyou
				+ ", yuangongJiekuanshenheyijian="
				+ yuangongJiekuanshenheyijian
				+ ", yuangongJiekuanhuankuanriqi="
				+ yuangongJiekuanhuankuanriqi
				+ ", yuangongJiekuanshifoujiechu="
				+ yuangongJiekuanshifoujiechu
				+ ", yuangongJiekuanshifouguihuan="
				+ yuangongJiekuanshifouguihuan + ", yuangongJk1=" + yuangongJk1
				+ ", yuangongJk2=" + yuangongJk2 + ", yuangongJk3="
				+ yuangongJk3 + ", yuangongJk4=" + yuangongJk4
				+ ", yuangongJk5=" + yuangongJk5 + ", yuangongJk6="
				+ yuangongJk6 + ", yuangongJk7=" + yuangongJk7
				+ ", yuangongJk8=" + yuangongJk8 + ", yuangongGongzifanian="
				+ yuangongGongzifanian + ", yuangongGongziyue="
				+ yuangongGongziyue + ", yuangongGongzifafangpizheng="
				+ yuangongGongzifafangpizheng
				+ ", yuangongGongzifafangshenpiyijian="
				+ yuangongGongzifafangshenpiyijian
				+ ", yuangongGongzifafangpishiqm="
				+ yuangongGongzifafangpishiqm + ", yuangongGongzishifoufafang="
				+ yuangongGongzishifoufafang + ", yuangongGongzifafangzongshu="
				+ yuangongGongzifafangzongshu + ", yuangongGongziA="
				+ yuangongGongziA + ", yuangongGongzi2=" + yuangongGongzi2
				+ ", yuangongGongzi3=" + yuangongGongzi3 + "]";
	}
}