package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author William
 * @Package The contract for YwhWebViewActivity
 * @Description: $description
 * @date 2018/11/14 14:14:11
 */
public interface YwhWebViewContract {
    interface View extends BaseView<YwhWebViewContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface YwhWebViewContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}