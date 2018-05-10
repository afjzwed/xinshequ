package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

/**
 * @author hu
 * @Package The contract for FangwuActivity
 * @Description: $description
 * @date 2017/06/06
 */
public interface FangwuContract {
    interface View extends BaseView<FangwuContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

    }

    interface FangwuContractPresenter extends BasePresenter {

    }
}