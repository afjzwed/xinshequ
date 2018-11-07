package com.yxld.yxchuangxin.ui.activity.wuye.ywh.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author William
 * @Package The contract for ResultShowActivity
 * @Description: $description
 * @date 2018/11/07 20:09:47
 */
public interface ResultShowContract {
    interface View extends BaseView<ResultShowContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface ResultShowContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}