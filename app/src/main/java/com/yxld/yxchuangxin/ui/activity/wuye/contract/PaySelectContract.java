package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for PaySelectActivity
 * @Description: $description
 * @date 2017/07/07 11:10:46
 */
public interface PaySelectContract {
    interface View extends BaseView<PaySelectContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 选择支付方式
         */
        void selectPaymentMethod(Integer method);

        /**
         * 支付宝支付
         */
        void alipayPay();

        /**
         * 微信支付
         */
        void weixinPay();

        /**
         * 银联支付
         */
        void yinlianPay();

        void jumpOrderListView();

        void requestAlipyError(String msg);

        void prePayBack(String orderBianhao);
    }

    interface PaySelectContractPresenter extends BasePresenter {
        void alipyPaySuccess(Map map);

        /**
         * 支付之前请求的接口
         * @param map
         */
        void prePay(Map map);
    }
}