package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author hzp
 * @Package The contract for MineShopActivity
 * @Description: $description
 * @date 2017/10/19 15:23:24
 */
public interface MineShopContract {
    interface View extends BaseView<MineShopContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface MineShopContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}