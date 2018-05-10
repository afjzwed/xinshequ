package com.yxld.yxchuangxin.ui.activity.rim.contract;

import com.yxld.yxchuangxin.entity.RimShopDetailBean;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author William
 * @Package The contract for RimShopDetailActivity
 * @Description: $description
 * @date 2017/12/15 12:04:35
 */
public interface RimShopDetailContract {
    interface View extends BaseView<RimShopDetailContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setData(RimShopDetailBean data);

        void setError();

        void setEmptyData(RimShopDetailBean data);
    }

    interface RimShopDetailContractPresenter extends BasePresenter {
//
        void getRimShopDetail(Map map);
    }
}