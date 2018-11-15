package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author William
 * @Package The contract for YwhBeianMemberActivity
 * @Description: $description
 * @date 2018/11/15 09:47:02
 */
public interface YwhBeianMemberContract {
    interface View extends BaseView<YwhBeianMemberContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface YwhBeianMemberContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}