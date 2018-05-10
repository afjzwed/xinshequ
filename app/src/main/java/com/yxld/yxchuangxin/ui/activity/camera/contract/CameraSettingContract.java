package com.yxld.yxchuangxin.ui.activity.camera.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for CameraSettingActivity
 * @Description: $description
 * @date 2017/06/21 10:21:20
 */
public interface CameraSettingContract {
    interface View extends BaseView<CameraSettingContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setNewPassWord();
    }

    interface CameraSettingContractPresenter extends BasePresenter {
        /**
         *获取摄像头更新
         */
        void getCameraUpdate(Map map);
    }
}