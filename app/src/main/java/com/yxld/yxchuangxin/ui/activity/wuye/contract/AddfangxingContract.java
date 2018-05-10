package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for AddfangxingActivity
 * @Description: $description
 * @date 2017/06/13
 */
public interface AddfangxingContract {
    interface View extends BaseView<AddfangxingContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 提交成功
         */
        void onComfirmSuccess();
    }

    interface AddfangxingContractPresenter extends BasePresenter {
        /**
         * 租户授权申请提交
         */
        void comfirmAccredit(Map map);
    }
}