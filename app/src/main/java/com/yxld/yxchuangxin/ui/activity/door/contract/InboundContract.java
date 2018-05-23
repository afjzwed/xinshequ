package com.yxld.yxchuangxin.ui.activity.door.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

/**
 * @author xlei
 * @Package The contract for InboundActivity
 * @Description: $description
 * @date 2018/04/19 14:12:55
 */
public interface InboundContract {
    interface View extends BaseView<InboundContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface InboundContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}