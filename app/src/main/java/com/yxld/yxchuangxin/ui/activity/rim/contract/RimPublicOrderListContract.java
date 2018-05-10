package com.yxld.yxchuangxin.ui.activity.rim.contract;

import android.os.Bundle;

import com.yxld.yxchuangxin.entity.SJComplain;
import com.yxld.yxchuangxin.entity.SJOrder;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.LinkedHashMap;

/**
 * @author wwx
 * @Package The contract for RimPublicOrderListFragment
 * @Description: $description
 * @date 2017/06/17
 */
public interface RimPublicOrderListContract {
    interface View extends BaseView<RimPublicOrderListContractPresenter> {
        void initData();

        void getData(boolean isRefresh);

        void setData(boolean isRefresh,SJOrder data);

        void setError();

        void setEmptyData();

        //请求完成 （提示消息,是否刷新,跳哪个页面）
        void requestFinish(String msg, boolean isRefresh);

        //获得投诉详情完成
        void getRimComplainDetailFinish(boolean isFinish, Bundle bundle, SJComplain data);

        void showProgressDialog();

        void closeProgressDialog();
        //取消订单弹窗
        void initCancelWindow();
        //页面数据状态改变
        void statusChange();
        //支付订单弹窗
        void initPayWindow();
        /** 选择支付方式 */
        void selectPaymentMethod();
        /** 支付宝支付*/
        void alipayPay();
        /** 微信支付 */
        void weixinPay();
        /** 银联支付*/
        void yinlianPay();

        void onAlipaySucceed();

        //跳转列表的方法
        void jump(int i);
    }

    interface RimPublicOrderListContractPresenter extends BasePresenter {
        //获取订单列表数据
        void getRimOrderList(LinkedHashMap<String, String> data,boolean isRefresh);

        //确认订单接口
        void requestConfirOrder(LinkedHashMap<String, String> data);

        //取消订单接口
        void requestCancelOrder(LinkedHashMap<String, String> data);

        /**
         * 获取订单详情接口
         */
        void getRimComplainDetail(LinkedHashMap<String, String> data, Bundle bundle);

        //删除订单
        void deleteRimOrder(LinkedHashMap<String, String> data);

        //确认订单商品库存的接口
        void getOrderBuyCheck(LinkedHashMap<String, String> data);
    }
}