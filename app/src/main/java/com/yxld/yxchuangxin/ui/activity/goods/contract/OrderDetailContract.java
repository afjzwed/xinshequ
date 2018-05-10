package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.entity.CxwyMallOrder;
import com.yxld.yxchuangxin.entity.OrderDetailEntity;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author Yuan.Y.Q
 * @Package The contract for OrderDetailActivity
 * @Description: $description
 * @date 2017/06/22 15:45:54
 */
public interface OrderDetailContract {
    interface View extends BaseView<OrderDetailContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void onLoadOrderDetailFailed();
        void onLoadOrderDetailSucceed(OrderDetailEntity entity);
    }

    interface OrderDetailContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        void loadOrderDetailFromServer(String orderId);
    }
}