package com.yxld.yxchuangxin.ui.activity.wuye.ywh.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author William
 * @Package The contract for YwhMemberShowActivity
 * @Description: $description
 * @date 2018/11/07 20:37:02
 */
public interface YwhMemberShowContract {
    interface View extends BaseView<YwhMemberShowContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface YwhMemberShowContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}