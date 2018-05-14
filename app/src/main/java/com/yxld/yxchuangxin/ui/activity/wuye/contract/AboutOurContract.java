package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.entity.LocalAd;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author hu
 * @Package The contract for AboutOurActivity
 * @Description: $description
 * @date 2017/06/23 09:20:36
 */
public interface AboutOurContract {
    interface View extends BaseView<AboutOurContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
        void setData(LocalAd localAd);
    }

    interface AboutOurContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getData();
    }
}