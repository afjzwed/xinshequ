package com.yxld.yxchuangxin.ui.activity.xiongmai.lib.sdk.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class ManageRemoteEnableBean {
	@JSONField(name = "RemoteEnable")
	private boolean remoteEnable;

	public boolean isRemoteEnable() {
		return remoteEnable;
	}

	public void setRemoteEnable(boolean remoteEnable) {
		this.remoteEnable = remoteEnable;
	}
}
