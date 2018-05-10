package com.yxld.yxchuangxin.ui.activity.camera.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author hu
 * @Package The contract for CameraConfigActivity
 * @Description: $description
 * @date 2017/06/21 10:21:40
 */
public interface CameraConfigContract {
    interface View extends BaseView<CameraConfigContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface CameraConfigContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}