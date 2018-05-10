package com.yxld.yxchuangxin.entity;

import java.util.List;

/**
 * Door entity. @author MyEclipse Persistence Tools
 */

public class Door {

	// Fields
	public List<Door> rows;

	private Integer id;
	private String machineIp;
	private String machineMac;
	private Long machinePort;
	private String houses;
	private String status;
	private String menjinname;
	private String leixing;
	private String dong;
	private String danyuan;
	private Long xiaoqubianhao;

	public Door(Integer id, String machineIp, String machineMac, Long machinePort, String houses, String status, String menjinname, String leixing, String dong, String danyuan, Long xiaoqubianhao) {
		this.id = id;
		this.machineIp = machineIp;
		this.machineMac = machineMac;
		this.machinePort = machinePort;
		this.houses = houses;
		this.status = status;
		this.menjinname = menjinname;
		this.leixing = leixing;
		this.dong = dong;
		this.danyuan = danyuan;
		this.xiaoqubianhao = xiaoqubianhao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMachineIp() {
		return machineIp;
	}

	public void setMachineIp(String machineIp) {
		this.machineIp = machineIp;
	}

	public String getMachineMac() {
		return machineMac;
	}

	public void setMachineMac(String machineMac) {
		this.machineMac = machineMac;
	}

	public Long getMachinePort() {
		return machinePort;
	}

	public void setMachinePort(Long machinePort) {
		this.machinePort = machinePort;
	}

	public String getHouses() {
		return houses;
	}

	public void setHouses(String houses) {
		this.houses = houses;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMenjinname() {
		return menjinname;
	}

	public void setMenjinname(String menjinname) {
		this.menjinname = menjinname;
	}

	public String getLeixing() {
		return leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getDanyuan() {
		return danyuan;
	}

	public void setDanyuan(String danyuan) {
		this.danyuan = danyuan;
	}

	public Long getXiaoqubianhao() {
		return xiaoqubianhao;
	}

	public void setXiaoqubianhao(Long xiaoqubianhao) {
		this.xiaoqubianhao = xiaoqubianhao;
	}

	public List<Door> getRows() {
		return rows;
	}

	public void setRows(List<Door> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "Door{" +
				"rows=" + rows +
				", id=" + id +
				", machineIp='" + machineIp + '\'' +
				", machineMac='" + machineMac + '\'' +
				", machinePort=" + machinePort +
				", houses='" + houses + '\'' +
				", status='" + status + '\'' +
				", menjinname='" + menjinname + '\'' +
				", leixing='" + leixing + '\'' +
				", dong='" + dong + '\'' +
				", danyuan='" + danyuan + '\'' +
				", xiaoqubianhao=" + xiaoqubianhao +
				'}';
	}
}