package com.yxld.yxchuangxin.ui.activity.mine.contract;

import com.yxld.yxchuangxin.entity.LoginEntity;
import com.yxld.yxchuangxin.entity.LoginPhoneEntity;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hzp
 * @Package The contract for AddAccountActivity
 * @Description: $description
 * @date 2017/10/11 09:49:50
 */
public interface AddAccountContract {
    interface View extends BaseView<AddAccountContractPresenter> {
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

    interface AddAccountContractPresenter extends BasePresenter {
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