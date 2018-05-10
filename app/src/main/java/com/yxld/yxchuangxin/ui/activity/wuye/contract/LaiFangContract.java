package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import android.content.Intent;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for LaiFangFragment
 * @Description: $description
 * @date 2017/06/06
 */
public interface LaiFangContract {
    interface View extends BaseView<LaiFangContractPresenter> {
        void setOnResult(String name, String number);
        void onQrBack();
    }

    interface LaiFangContractPresenter extends BasePresenter {
        void onActivityResult(int requestCode, int resultCode, Intent data);
        void getQrCode(Map data);
    }
}