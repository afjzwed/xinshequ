package com.yxld.yxchuangxin.ui.activity.xiongmai.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author hu
 * @Package The contract for XCameraActivity
 * @Description: $description
 * @date 2017/07/17 16:30:10
 */
public interface XCameraContract {
    interface View extends BaseView<XCameraContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface XCameraContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}