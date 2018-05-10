package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.entity.goods.MallOrderSuggest;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

/**
 * @author xlei
 * @Package The contract for MarketComplainListActivity
 * @Description: $description
 * @date 2017/11/02 14:28:33
 */
public interface MarketComplainListContract {
    interface View extends BaseView<MarketComplainListContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void loadSuggestListSuccess(MallOrderSuggest mallOrderSuggest);

    }

    interface MarketComplainListContractPresenter extends BasePresenter {
        void getSuggestListFromSever();
    }
}