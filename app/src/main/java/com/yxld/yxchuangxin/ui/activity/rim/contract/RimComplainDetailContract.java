package com.yxld.yxchuangxin.ui.activity.rim.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author William
 * @Package The contract for RimComplainDetailActivity
 * @Description: $description
 * @date 2017/12/21 15:47:26
 */
public interface RimComplainDetailContract {
    interface View extends BaseView<RimComplainDetailContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface RimComplainDetailContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}