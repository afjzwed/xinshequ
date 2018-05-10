package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.entity.goods.MallOrderSuggest;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

/**
 * @author Yuan.Y.Q
 * @Package The contract for OrderComplainActivity
 * @Description: $description
 * @date 2017/06/22 18:01:39
 */
public interface OrderComplainContract {
    interface View extends BaseView<OrderComplainContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void getCompainOrderSucceed(MallOrderSuggest mallOrderSuggest);
    }

    interface OrderComplainContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getCompainOrder(String orderid);
    }
}