package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author Yuan.Y.Q
 * @Package The contract for SecondShopCartActivity
 * @Description: $description
 * @date 2017/07/05 14:05:30
 */
public interface SecondShopCartContract {
    interface View extends BaseView<SecondShopCartContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface SecondShopCartContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}