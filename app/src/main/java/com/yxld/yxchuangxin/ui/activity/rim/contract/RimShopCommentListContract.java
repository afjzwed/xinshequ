package com.yxld.yxchuangxin.ui.activity.rim.contract;

import com.yxld.yxchuangxin.entity.RimCommentListBean;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author RimShopCommentList
 * @Package The contract for RimShopCommentListActivity
 * @Description: $description
 * @date 2017/12/18 13:46:52
 */
public interface RimShopCommentListContract {
    interface View extends BaseView<RimShopCommentListContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setData(boolean isRefresh, RimCommentListBean data);

        void setError();

        void setEmptyData(RimCommentListBean data);
    }

    interface RimShopCommentListContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getRimShopCommentList(Map map, boolean isRefresh);
    }
}