package com.yxld.yxchuangxin.ui.activity.xiongmai.lib.funsdk.support;


import com.yxld.yxchuangxin.ui.activity.xiongmai.lib.funsdk.support.models.FunDevRecordFile;

import java.util.List;

public interface OnFunDeviceRecordListener extends OnFunListener {

    void onRequestRecordListSuccess(List<FunDevRecordFile> files);

    void onRequestRecordListFailed(final Integer errCode);

}
