package com.yxld.yxchuangxin.ui.activity.mine.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for ResetPasswordActivity
 * @Description: $description
 * @date 2017/06/23 11:03:59
 */
public interface ResetPasswordContract {
    interface View extends BaseView<ResetPasswordContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 密码修改成功
         */
        void onUpdatePasswordSuccess();

        /**
         * 退出登录
         */
        void loginOut();
    }

    interface ResetPasswordContractPresenter extends BasePresenter {
        /**
         *重新设置密码
         */
        void getUpdatePwd(Map map);

        /**
         * 退出登录
         */
        void getLoginOut(Map map);
    }
}