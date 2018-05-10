package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for AccountActivity
 * @Description: $description
 * @date 2017/06/06
 */
public interface AccountContract {
    interface View extends BaseView<AccountContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 退出登录
         */
        void loginOut();
    }

    interface AccountContractPresenter extends BasePresenter {
        void getLoginOut(Map map);
    }
}