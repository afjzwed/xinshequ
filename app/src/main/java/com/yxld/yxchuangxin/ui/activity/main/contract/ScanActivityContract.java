package com.yxld.yxchuangxin.ui.activity.main.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for ScanActivityActivity
 * @Description: $description
 * @date 2018/03/02 09:11:52
 */
public interface ScanActivityContract {
    interface View extends BaseView<ScanActivityContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface ScanActivityContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}