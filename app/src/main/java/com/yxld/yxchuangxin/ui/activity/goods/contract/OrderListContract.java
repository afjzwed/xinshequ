package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author Yuan.Y.Q
 * @Package The contract for OrderListActivity
 * @Description: $description
 * @date 2017/06/21 17:03:40
 */
public interface OrderListContract {
    interface View extends BaseView<OrderListContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface OrderListContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}