package com.yxld.yxchuangxin.ui.activity.xiongmai.lib.sdk.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class ExposureParamEnableBean {
	@JSONField(name = "Level")
	private boolean level;

	public boolean isLevel() {
		return level;
	}

	public void setLevel(boolean level) {
		this.level = level;
	}
}
