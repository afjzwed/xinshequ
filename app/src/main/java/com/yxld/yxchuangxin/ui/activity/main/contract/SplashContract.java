package com.yxld.yxchuangxin.ui.activity.main.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for SplashActivity
 * @Description: $description
 * @date 2017/06/05
 */
public interface SplashContract {
    interface View extends BaseView<SplashContractPresenter> {
        /**
         * 登录成功，跳转到主页面
         */
        void loginSuccees();

        /**
         * 跳转到登录界面
         */
        void jumpToLogin();

        /**
         * 跳转到欢迎页面
         */
        void iumpToGuest();
    }

    interface SplashContractPresenter extends BasePresenter {
        /**
         * 查询是否已经记录了用户信息
         */
        void queryShipperInfo();

        /**
         * 登录请求
         * @param map
         */
        void login(Map map);

        /**
         * 判断接下来跳转的页面
         */
        void observeJump();

        /**
         * 判断是不是更新了版本
         */
        boolean isUpdate();

        /**
         * 获取最新的版本号
         */
        void getLastVersion();

        /**
         * 判断权限
         */
        void getUpdatePermission();
    }
}