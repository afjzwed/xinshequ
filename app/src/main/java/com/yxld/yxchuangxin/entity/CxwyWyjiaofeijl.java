package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * CxwyWyjiaofeijl entity. @author MyEclipse Persistence Tools
 */

public class CxwyWyjiaofeijl extends BaseEntity implements java.io.Serializable {

	private List<CxwyWyjiaofeijl> rows;

	// Fields

	@Override
	public String toString() {
		return "CxwyWyjiaofeijl [rows=" + rows + ", wyjiaofeijlId="
				+ wyjiaofeijlId + ", wyjiaofeijlLoupan=" + wyjiaofeijlLoupan
				+ ", wyjiaofeijlLoudong=" + wyjiaofeijlLoudong
				+ ", wyjiaofeijlDanyuan=" + wyjiaofeijlDanyuan
				+ ", wyjiaofeijlFanghao=" + wyjiaofeijlFanghao
				+ ", wyjiaofeijlYezhu=" + wyjiaofeijlYezhu
				+ ", wyjiaofeijlAaw1=" + wyjiaofeijlAaw1 + ", wyjiaofeijlAaw2="
				+ wyjiaofeijlAaw2 + ", wyjiaofeijlJiaofeijine="
				+ wyjiaofeijlJiaofeijine + ", wyjiaofeijlJfsj="
				+ wyjiaofeijlJfsj + ", wyjiaofeijlFwzt=" + wyjiaofeijlFwzt
				+ ", wyjiaofeijlYj=" + wyjiaofeijlYj + ", wyjiaofeijlWj="
				+ wyjiaofeijlWj + ", wyjiaofeijlAaq1=" + wyjiaofeijlAaq1
				+ ", wyjiaofeijlAaq2=" + wyjiaofeijlAaq2 + ", wyjiaofeijlAaq3="
				+ wyjiaofeijlAaq3 + ", wyjiaofeijlAaq4=" + wyjiaofeijlAaq4
				+ ", wyjiaofeijlAaq5=" + wyjiaofeijlAaq5 + ", wyjiaofeijlAaq6="
				+ wyjiaofeijlAaq6 + ", wyjiaofeijlAaq7=" + wyjiaofeijlAaq7
				+ ", wyjiaofeijlAaq8=" + wyjiaofeijlAaq8 + ", wyjiaofeijlAaq9="
				+ wyjiaofeijlAaq9 + ", wyjiaofeijlAaw3=" + wyjiaofeijlAaw3
				+ ", wyjiaofeijlAaw4=" + wyjiaofeijlAaw4 + ", wyjiaofeijlAaw5="
				+ wyjiaofeijlAaw5 + ", wyjiaofeijlAaw6=" + wyjiaofeijlAaw6
				+ ", wyjiaofeijlAaw7=" + wyjiaofeijlAaw7 + ", wyjiaofeijlAaw8="
				+ wyjiaofeijlAaw8 + ", wyjiaofeijlAaw9=" + wyjiaofeijlAaw9
				+ ", status=" + status + ", MSG=" + MSG + "]";
	}

	public List<CxwyWyjiaofeijl> getRows() {
		return rows;
	}

	public void setRows(List<CxwyWyjiaofeijl> rows) {
		this.rows = rows;
	}

	private Integer wyjiaofeijlId;
	private String wyjiaofeijlLoupan;// 楼盘
	private String wyjiaofeijlLoudong;// 楼栋
	private String wyjiaofeijlDanyuan;// 单元
	private String wyjiaofeijlFanghao;// 房号
	private String wyjiaofeijlYezhu;// 业主
	private String wyjiaofeijlAaw1;// 开始时间
	private String wyjiaofeijlAaw2;// 结束时间
	private String wyjiaofeijlJiaofeijine;// 缴费金额
	private String wyjiaofeijlJfsj;// 缴费时间

	private String wyjiaofeijlFwzt;// 房屋状态
	private String wyjiaofeijlYj;// 已缴
	private String wyjiaofeijlWj;// 未缴
	private String wyjiaofeijlAaq1;// 欠物业费

	private String wyjiaofeijlAaq2;//
	private String wyjiaofeijlAaq3;
	private String wyjiaofeijlAaq4;
	private String wyjiaofeijlAaq5;
	private String wyjiaofeijlAaq6;
	private String wyjiaofeijlAaq7;
	private String wyjiaofeijlAaq8;
	private String wyjiaofeijlAaq9;

	private String wyjiaofeijlAaw3;
	private String wyjiaofeijlAaw4;
	private String wyjiaofeijlAaw5;
	private String wyjiaofeijlAaw6;
	private String wyjiaofeijlAaw7;
	private String wyjiaofeijlAaw8;
	private String wyjiaofeijlAaw9;

	// Constructors

	/** default constructor */
	public CxwyWyjiaofeijl() {
	}

	/** full constructor */
	public CxwyWyjiaofeijl(String wyjiaofeijlLoupan, String wyjiaofeijlLoudong,
                           String wyjiaofeijlDanyuan, String wyjiaofeijlFanghao,
                           String wyjiaofeijlYezhu, String wyjiaofeijlFwzt,
                           String wyjiaofeijlJiaofeijine, String wyjiaofeijlJfsj,
                           String wyjiaofeijlYj, String wyjiaofeijlWj, String wyjiaofeijlAaq1,
                           String wyjiaofeijlAaq2, String wyjiaofeijlAaq3,
                           String wyjiaofeijlAaq4, String wyjiaofeijlAaq5,
                           String wyjiaofeijlAaq6, String wyjiaofeijlAaq7,
                           String wyjiaofeijlAaq8, String wyjiaofeijlAaq9,
                           String wyjiaofeijlAaw1, String wyjiaofeijlAaw2,
                           String wyjiaofeijlAaw3, String wyjiaofeijlAaw4,
                           String wyjiaofeijlAaw5, String wyjiaofeijlAaw6,
                           String wyjiaofeijlAaw7, String wyjiaofeijlAaw8,
                           String wyjiaofeijlAaw9) {
		this.wyjiaofeijlLoupan = wyjiaofeijlLoupan;
		this.wyjiaofeijlLoudong = wyjiaofeijlLoudong;
		this.wyjiaofeijlDanyuan = wyjiaofeijlDanyuan;
		this.wyjiaofeijlFanghao = wyjiaofeijlFanghao;
		this.wyjiaofeijlYezhu = wyjiaofeijlYezhu;
		this.wyjiaofeijlFwzt = wyjiaofeijlFwzt;
		this.wyjiaofeijlJiaofeijine = wyjiaofeijlJiaofeijine;
		this.wyjiaofeijlJfsj = wyjiaofeijlJfsj;
		this.wyjiaofeijlYj = wyjiaofeijlYj;
		this.wyjiaofeijlWj = wyjiaofeijlWj;
		this.wyjiaofeijlAaq1 = wyjiaofeijlAaq1;
		this.wyjiaofeijlAaq2 = wyjiaofeijlAaq2;
		this.wyjiaofeijlAaq3 = wyjiaofeijlAaq3;
		this.wyjiaofeijlAaq4 = wyjiaofeijlAaq4;
		this.wyjiaofeijlAaq5 = wyjiaofeijlAaq5;
		this.wyjiaofeijlAaq6 = wyjiaofeijlAaq6;
		this.wyjiaofeijlAaq7 = wyjiaofeijlAaq7;
		this.wyjiaofeijlAaq8 = wyjiaofeijlAaq8;
		this.wyjiaofeijlAaq9 = wyjiaofeijlAaq9;
		this.wyjiaofeijlAaw1 = wyjiaofeijlAaw1;
		this.wyjiaofeijlAaw2 = wyjiaofeijlAaw2;
		this.wyjiaofeijlAaw3 = wyjiaofeijlAaw3;
		this.wyjiaofeijlAaw4 = wyjiaofeijlAaw4;
		this.wyjiaofeijlAaw5 = wyjiaofeijlAaw5;
		this.wyjiaofeijlAaw6 = wyjiaofeijlAaw6;
		this.wyjiaofeijlAaw7 = wyjiaofeijlAaw7;
		this.wyjiaofeijlAaw8 = wyjiaofeijlAaw8;
		this.wyjiaofeijlAaw9 = wyjiaofeijlAaw9;
	}

	// Property accessors

	public Integer getWyjiaofeijlId() {
		return this.wyjiaofeijlId;
	}

	public void setWyjiaofeijlId(Integer wyjiaofeijlId) {
		this.wyjiaofeijlId = wyjiaofeijlId;
	}

	public String getWyjiaofeijlLoupan() {
		return this.wyjiaofeijlLoupan;
	}

	public void setWyjiaofeijlLoupan(String wyjiaofeijlLoupan) {
		this.wyjiaofeijlLoupan = wyjiaofeijlLoupan;
	}

	public String getWyjiaofeijlLoudong() {
		return this.wyjiaofeijlLoudong;
	}

	public void setWyjiaofeijlLoudong(String wyjiaofeijlLoudong) {
		this.wyjiaofeijlLoudong = wyjiaofeijlLoudong;
	}

	public String getWyjiaofeijlDanyuan() {
		return this.wyjiaofeijlDanyuan;
	}

	public void setWyjiaofeijlDanyuan(String wyjiaofeijlDanyuan) {
		this.wyjiaofeijlDanyuan = wyjiaofeijlDanyuan;
	}

	public String getWyjiaofeijlFanghao() {
		return this.wyjiaofeijlFanghao;
	}

	public void setWyjiaofeijlFanghao(String wyjiaofeijlFanghao) {
		this.wyjiaofeijlFanghao = wyjiaofeijlFanghao;
	}

	public String getWyjiaofeijlYezhu() {
		return this.wyjiaofeijlYezhu;
	}

	public void setWyjiaofeijlYezhu(String wyjiaofeijlYezhu) {
		this.wyjiaofeijlYezhu = wyjiaofeijlYezhu;
	}

	public String getWyjiaofeijlFwzt() {
		return this.wyjiaofeijlFwzt;
	}

	public void setWyjiaofeijlFwzt(String wyjiaofeijlFwzt) {
		this.wyjiaofeijlFwzt = wyjiaofeijlFwzt;
	}

	public String getWyjiaofeijlJiaofeijine() {
		return this.wyjiaofeijlJiaofeijine;
	}

	public void setWyjiaofeijlJiaofeijine(String wyjiaofeijlJiaofeijine) {
		this.wyjiaofeijlJiaofeijine = wyjiaofeijlJiaofeijine;
	}

	public String getWyjiaofeijlJfsj() {
		return this.wyjiaofeijlJfsj;
	}

	public void setWyjiaofeijlJfsj(String wyjiaofeijlJfsj) {
		this.wyjiaofeijlJfsj = wyjiaofeijlJfsj;
	}

	public String getWyjiaofeijlYj() {
		return this.wyjiaofeijlYj;
	}

	public void setWyjiaofeijlYj(String wyjiaofeijlYj) {
		this.wyjiaofeijlYj = wyjiaofeijlYj;
	}

	public String getWyjiaofeijlWj() {
		return this.wyjiaofeijlWj;
	}

	public void setWyjiaofeijlWj(String wyjiaofeijlWj) {
		this.wyjiaofeijlWj = wyjiaofeijlWj;
	}

	public String getWyjiaofeijlAaq1() {
		return this.wyjiaofeijlAaq1;
	}

	public void setWyjiaofeijlAaq1(String wyjiaofeijlAaq1) {
		this.wyjiaofeijlAaq1 = wyjiaofeijlAaq1;
	}

	public String getWyjiaofeijlAaq2() {
		return this.wyjiaofeijlAaq2;
	}

	public void setWyjiaofeijlAaq2(String wyjiaofeijlAaq2) {
		this.wyjiaofeijlAaq2 = wyjiaofeijlAaq2;
	}

	public String getWyjiaofeijlAaq3() {
		return this.wyjiaofeijlAaq3;
	}

	public void setWyjiaofeijlAaq3(String wyjiaofeijlAaq3) {
		this.wyjiaofeijlAaq3 = wyjiaofeijlAaq3;
	}

	public String getWyjiaofeijlAaq4() {
		return this.wyjiaofeijlAaq4;
	}

	public void setWyjiaofeijlAaq4(String wyjiaofeijlAaq4) {
		this.wyjiaofeijlAaq4 = wyjiaofeijlAaq4;
	}

	public String getWyjiaofeijlAaq5() {
		return this.wyjiaofeijlAaq5;
	}

	public void setWyjiaofeijlAaq5(String wyjiaofeijlAaq5) {
		this.wyjiaofeijlAaq5 = wyjiaofeijlAaq5;
	}

	public String getWyjiaofeijlAaq6() {
		return this.wyjiaofeijlAaq6;
	}

	public void setWyjiaofeijlAaq6(String wyjiaofeijlAaq6) {
		this.wyjiaofeijlAaq6 = wyjiaofeijlAaq6;
	}

	public String getWyjiaofeijlAaq7() {
		return this.wyjiaofeijlAaq7;
	}

	public void setWyjiaofeijlAaq7(String wyjiaofeijlAaq7) {
		this.wyjiaofeijlAaq7 = wyjiaofeijlAaq7;
	}

	public String getWyjiaofeijlAaq8() {
		return this.wyjiaofeijlAaq8;
	}

	public void setWyjiaofeijlAaq8(String wyjiaofeijlAaq8) {
		this.wyjiaofeijlAaq8 = wyjiaofeijlAaq8;
	}

	public String getWyjiaofeijlAaq9() {
		return this.wyjiaofeijlAaq9;
	}

	public void setWyjiaofeijlAaq9(String wyjiaofeijlAaq9) {
		this.wyjiaofeijlAaq9 = wyjiaofeijlAaq9;
	}

	public String getWyjiaofeijlAaw1() {
		return this.wyjiaofeijlAaw1;
	}

	public void setWyjiaofeijlAaw1(String wyjiaofeijlAaw1) {
		this.wyjiaofeijlAaw1 = wyjiaofeijlAaw1;
	}

	public String getWyjiaofeijlAaw2() {
		return this.wyjiaofeijlAaw2;
	}

	public void setWyjiaofeijlAaw2(String wyjiaofeijlAaw2) {
		this.wyjiaofeijlAaw2 = wyjiaofeijlAaw2;
	}

	public String getWyjiaofeijlAaw3() {
		return this.wyjiaofeijlAaw3;
	}

	public void setWyjiaofeijlAaw3(String wyjiaofeijlAaw3) {
		this.wyjiaofeijlAaw3 = wyjiaofeijlAaw3;
	}

	public String getWyjiaofeijlAaw4() {
		return this.wyjiaofeijlAaw4;
	}

	public void setWyjiaofeijlAaw4(String wyjiaofeijlAaw4) {
		this.wyjiaofeijlAaw4 = wyjiaofeijlAaw4;
	}

	public String getWyjiaofeijlAaw5() {
		return this.wyjiaofeijlAaw5;
	}

	public void setWyjiaofeijlAaw5(String wyjiaofeijlAaw5) {
		this.wyjiaofeijlAaw5 = wyjiaofeijlAaw5;
	}

	public String getWyjiaofeijlAaw6() {
		return this.wyjiaofeijlAaw6;
	}

	public void setWyjiaofeijlAaw6(String wyjiaofeijlAaw6) {
		this.wyjiaofeijlAaw6 = wyjiaofeijlAaw6;
	}

	public String getWyjiaofeijlAaw7() {
		return this.wyjiaofeijlAaw7;
	}

	public void setWyjiaofeijlAaw7(String wyjiaofeijlAaw7) {
		this.wyjiaofeijlAaw7 = wyjiaofeijlAaw7;
	}

	public String getWyjiaofeijlAaw8() {
		return this.wyjiaofeijlAaw8;
	}

	public void setWyjiaofeijlAaw8(String wyjiaofeijlAaw8) {
		this.wyjiaofeijlAaw8 = wyjiaofeijlAaw8;
	}

	public String getWyjiaofeijlAaw9() {
		return this.wyjiaofeijlAaw9;
	}

	public void setWyjiaofeijlAaw9(String wyjiaofeijlAaw9) {
		this.wyjiaofeijlAaw9 = wyjiaofeijlAaw9;
	}

}