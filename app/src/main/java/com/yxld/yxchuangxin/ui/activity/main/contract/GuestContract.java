package com.yxld.yxchuangxin.ui.activity.main.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author hu
 * @Package The contract for GuestActivity
 * @Description: $description
 * @date 2017/06/30 10:41:13
 */
public interface GuestContract {
    interface View extends BaseView<GuestContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface GuestContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}