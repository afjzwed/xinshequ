package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.entity.CxwyAppVersion;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for UpdateActivity
 * @Description: $description
 * @date 2017/06/23 09:25:59
 */
public interface UpdateContract {
    interface View extends BaseView<UpdateContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
        void setLastVersion(CxwyAppVersion version);

        /**
         * loginOut成功
         */
        void loginOutSuccess();
    }

    interface UpdateContractPresenter extends BasePresenter {
        /**
         * 获取最新的版本号
         */
        void getLastVersion();

        /**
         * 判断权限
         */
        void getUpdatePermission();

        void getLoginOut(Map map);
    }
}