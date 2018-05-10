package com.yxld.yxchuangxin.ui.activity.camera.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for CameraActivity
 * @Description: $description
 * @date 2017/06/21 10:19:03
 */
public interface CameraContract {
    interface View extends BaseView<CameraContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface CameraContractPresenter extends BasePresenter {

    }
}