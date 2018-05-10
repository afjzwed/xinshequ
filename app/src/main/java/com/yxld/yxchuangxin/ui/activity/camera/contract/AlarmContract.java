package com.yxld.yxchuangxin.ui.activity.camera.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author hu
 * @Package The contract for AlarmActivity
 * @Description: $description
 * @date 2017/06/21 10:23:03
 */
public interface AlarmContract {
    interface View extends BaseView<AlarmContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface AlarmContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}