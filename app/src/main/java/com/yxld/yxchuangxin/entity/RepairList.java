package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

public class RepairList extends BaseEntity {

	public List<CxwyXiangmu> rows;

	public List<CxwyXiangmu> getRows() {
		return rows;
	}

	public void setRows(List<CxwyXiangmu> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "Household [rows=" + rows + ", status=" + status + ", MSG="
				+ MSG + "]";
	}
}
