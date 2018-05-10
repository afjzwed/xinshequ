package com.yxld.yxchuangxin.ui.activity.camera.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author hu
 * @Package The contract for RecordFilesActivity
 * @Description: $description
 * @date 2017/06/21 10:22:29
 */
public interface RecordFilesContract {
    interface View extends BaseView<RecordFilesContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface RecordFilesContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}