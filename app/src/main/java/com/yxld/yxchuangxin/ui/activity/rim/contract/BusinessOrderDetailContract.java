package com.yxld.yxchuangxin.ui.activity.rim.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.db.green.SPInfo;
import com.yxld.yxchuangxin.entity.CxwyOrderInfo;
import com.yxld.yxchuangxin.entity.ShopCarList;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.List;
import java.util.Map;

/**
 * @author hu
 * @Package The contract for BusinessOrderDetailActivity
 * @Description: $description
 * @date 2017/06/20 14:05:57
 */
public interface BusinessOrderDetailContract {
    interface View extends BaseView<BusinessOrderDetailContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 获取订单详情之后，显示订单信息
         */
        void setOrderInfo(CxwyOrderInfo info, List<ShopCarList.ShopCarBean> list);

        /**
         * 设置产品数量和设置价格
         */
        void setCountAndPrice(String count);

        /**
         * 设置产品数量和设置价格
         */
        void detailsToCarSuccess(BaseEntity baseEntity);
    }

    interface BusinessOrderDetailContractPresenter extends BasePresenter {
        /**
         * 获取订单详情
         */
        void getBusinessOrderDetail(Map map);

        /**
         * 操作订单， 包括取消，支付，评价和确认送达
         */
        void opreateOrder(android.view.View v);

        /**
         * 动态
         */
        void getOrderDynamic();

        /**
         * 投诉
         */
        void Complain();

        void detailToCar(Map map);
    }
}