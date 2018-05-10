package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author wwx
 * @Package The contract for PayWaySelectActivity
 * @Description: $description
 * @date 2017/06/27 16:12:49
 */
public interface PayWaySelectContract {
    interface View extends BaseView<PayWaySelectContractPresenter> {

        void showProgressDialog();

        void closeProgressDialog();

        /** 选择支付方式 */
        void selectPaymentMethod(Integer method);

        /** 支付宝支付*/
        void alipayPay();

        /** 微信支付 */
        void weixinPay();

        /** 银联支付*/
        void yinlianPay();

        void jumpOrderListView();

        void requestAlipyError(String msg);

        void onAlipaySucceed();
    }

    interface PayWaySelectContractPresenter extends BasePresenter {
        void alipyPaySuccess(Map map);
    }
}