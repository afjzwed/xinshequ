package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * @ClassName: WuyeRecordAndroid
 * @Description: 查询缴费时构建中间对象
 * @author wwx
 * @date 2016年4月15日 下午17:41:29
 */
public class WuyeRecordAndroid extends BaseEntity {
	private String QFZS;
	
	private List<WuyeRecordAndroid> rows;
	
	/** id*/
	private int id;
	/** 缴费时间*/
	private String time;
	/** 费用合计*/
	private String mmoney;
	/** 费用类型:例如物业费、水费、电费...*/
	private String type;
	/** 备用字段 存放结束时间*/
	private String beiyong;
	
	private boolean isChecked = false;
	
	public WuyeRecordAndroid() {
	}
	public WuyeRecordAndroid(String qWYFZS, List<WuyeRecordAndroid> rows,
                             int id, String time, String mmoney, String type, String beiyong,
                             boolean isChecked) {
		super();
		QFZS = qWYFZS;
		this.rows = rows;
		this.id = id;
		this.time = time;
		this.mmoney = mmoney;
		this.type = type;
		this.beiyong = beiyong;
		this.isChecked = isChecked;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMmoney() {
		return mmoney;
	}
	public void setMmoney(String mmoney) {
		this.mmoney = mmoney;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBeiyong() {
		return beiyong;
	}
	public void setBeiyong(String beiyong) {
		this.beiyong = beiyong;
	}
	public String getQWYFZS() {
		return QFZS;
	}
	public void setQWYFZS(String qWYFZS) {
		QFZS = qWYFZS;
	}
	public List<WuyeRecordAndroid> getRows() {
		return rows;
	}
	public void setRows(List<WuyeRecordAndroid> rows) {
		this.rows = rows;
	}
	
	
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	@Override
	public String toString() {
		return "WuyeRecordAndroid [QWYFZS=" + QFZS + ", rows=" + rows
				+ ", id=" + id + ", time=" + time + ", mmoney=" + mmoney
				+ ", type=" + type + ", beiyong=" + beiyong + ", isChecked="
				+ isChecked + ", status=" + status + ", MSG=" + MSG + "]";
	}
	
}
