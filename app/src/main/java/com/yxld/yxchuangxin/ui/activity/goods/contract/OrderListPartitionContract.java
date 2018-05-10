package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.CxwyMallOrder;
import com.yxld.yxchuangxin.entity.goods.BaseEntityAll;
import com.yxld.yxchuangxin.entity.goods.MallNewOrder;
import com.yxld.yxchuangxin.entity.goods.MallOrderSuggest;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author Yuan.Y.Q
 * @Package The contract for OrderListPartitionFragment
 * @Description: $description
 * @date 2017/06/21 17:10:04
 */
public interface OrderListPartitionContract {
    interface View extends BaseView<OrderListPartitionContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void onOrderDataBacked(MallNewOrder orders);

        void onLoadDataFailed();

        void onUpdateOrderStatusSucceed(BaseEntity entity, int status);

        void onLoadCompainOrderListSucceed(MallOrderSuggest mallOrderSuggest,String ordid);
        void onCheckPayNowSucceed(BaseEntityAll entity, Map<String, String> params);
    }

    interface OrderListPartitionContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getOrderListFromServer(String orderStatus, int requestPage, int onePageSize);

        void updateOrderStatus(String orderId, int status, String reason);

        void updateOrderStatusForTuiKuan(String orderId, int status, String reason);

        void getComplainOrderList(String ordid);

        void checkPayNow(Map<String, String> contanier);

    }
}