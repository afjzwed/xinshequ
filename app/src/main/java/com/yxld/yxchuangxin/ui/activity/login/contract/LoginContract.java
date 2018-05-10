package com.yxld.yxchuangxin.ui.activity.login.contract;

import com.yxld.yxchuangxin.entity.LoginEntity;
import com.yxld.yxchuangxin.entity.LoginPhoneEntity;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for LoginActivity
 * @Description: $description
 * @date 2017/05/23
 */
public interface LoginContract {
    interface View extends BaseView<LoginContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 设置登录项目
         * @param loginPhone
         */
        void setLoginPhone(LoginPhoneEntity loginPhone);
        void login(LoginEntity loginEntity);
    }

    interface LoginContractPresenter extends BasePresenter {
        /**
         * 登录客户端
         * @param map
         */
        void login(Map map);

        /**
         * 获取项目
         * @param map
         */
        void loginPlot(Map map);
    }
}