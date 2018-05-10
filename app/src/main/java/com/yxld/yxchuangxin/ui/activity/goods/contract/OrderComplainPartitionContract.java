package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.entity.CxwyComplain;
import com.yxld.yxchuangxin.entity.OrderComplainEntity;
import com.yxld.yxchuangxin.entity.goods.MallOrderSuggest;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.List;

/**
 * @author Yuan.Y.Q
 * @Package The contract for OrderComplainPartitionFragment
 * @Description: $description
 * @date 2017/06/22 18:31:29
 */
public interface OrderComplainPartitionContract {
    interface View extends BaseView<OrderComplainPartitionContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void onOrderComplainDataBacked(MallOrderSuggest entities);

        void onLoadDataFailed();
    }

    interface OrderComplainPartitionContractPresenter extends BasePresenter {

        void getOrderComplainDataFromServer(String type,int nextPage,int onePageSize);
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}