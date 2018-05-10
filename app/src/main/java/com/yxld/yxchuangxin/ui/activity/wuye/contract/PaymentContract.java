package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for PaymentFragment
 * @Description: $description
 * @date 2017/07/01 13:45:55
 */
public interface PaymentContract {
    interface View extends BaseView<PaymentContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setList();

        void setMoreList();
    }

    interface PaymentContractPresenter extends BasePresenter {
        /**
         *
         */
        void getList(Map map);

        /**
         *
         */
        void getJiaofei(Map map);
    }
}