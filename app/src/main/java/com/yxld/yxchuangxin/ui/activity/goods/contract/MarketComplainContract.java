package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for MarketComplainActivity
 * @Description: $description
 * @date 2017/06/22 15:30:24
 */
public interface MarketComplainContract {
    interface View extends BaseView<MarketComplainContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();


        void onComplainSucceed(BaseEntity entity);
        void onComplainFailed();
    }

    interface MarketComplainContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void toComplain(Map<String, String> params);


    }
}