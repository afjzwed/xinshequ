package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

public class CartEntity extends BaseEntity {

	private int id;
	private String name;
	private int count;
	private String praise;
	private boolean isChecked;
	
	public CartEntity(String name) {
		super();
		this.name = name;
	}

	public CartEntity(String name, int count) {
		super();
		this.name = name;
		this.count = count;
	}

	public CartEntity(String name, int count, String praise) {
		super();
		this.name = name;
		this.count = count;
		this.praise = praise;
	}
	
	public CartEntity(int id, String name, int count, String praise,
                      boolean isChecked) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
		this.praise = praise;
		this.isChecked = isChecked;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getPraise() {
		return praise;
	}
	public void setPraise(String praise) {
		this.praise = praise;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CartEntity [id=" + id + ", name=" + name + ", count=" + count
				+ ", praise=" + praise + ", isChecked=" + isChecked + "]";
	}
	
	
}
