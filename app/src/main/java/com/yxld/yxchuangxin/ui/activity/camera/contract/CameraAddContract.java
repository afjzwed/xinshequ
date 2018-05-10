package com.yxld.yxchuangxin.ui.activity.camera.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for CameraAddActivity
 * @Description: $description
 * @date 2017/06/21 10:21:55
 */
public interface CameraAddContract {
    interface View extends BaseView<CameraAddContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface CameraAddContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        /**
         *添加摄像头
         */
        void cameraAdd(Map map);
    }
}