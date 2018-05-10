package com.yxld.yxchuangxin.ui.activity.mine.contract;

import com.yxld.yxchuangxin.db.green.UserInfo;
import com.yxld.yxchuangxin.entity.LoginEntity;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hzp
 * @Package The contract for MultiAccountActivity
 * @Description: $description
 * @date 2017/10/11 09:31:27
 */
public interface MultiAccountContract {
    interface View extends BaseView<MultiAccountContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 修改登录改变的ui
         * @param userInfo
         */
        void loginSuccess(UserInfo userInfo);

        /**
         * 修改登录信息
         * @param loginEntity
         */
        void loginSuccessPre(LoginEntity loginEntity);
    }

    interface MultiAccountContractPresenter extends BasePresenter {
        void switchAccount(UserInfo userInfo);

        void login(Map map, UserInfo userInfo);
    }
}