package com.yxld.yxchuangxin.ui.activity.xiongmai.contract;

import com.yxld.yxchuangxin.entity.XMsxt;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for DeviceLoginActivity
 * @Description: $description
 * @date 2017/07/18 09:30:54
 */
public interface DeviceLoginContract {
    interface View extends BaseView<DeviceLoginContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setAdapter(XMsxt xMsxt);
    }

    interface DeviceLoginContractPresenter extends BasePresenter {
        /**
         *
         */
        void getSXT(Map map);
    }
}