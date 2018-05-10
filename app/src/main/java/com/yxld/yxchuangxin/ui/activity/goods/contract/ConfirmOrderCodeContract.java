package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.goods.BaseEntityAll;
import com.yxld.yxchuangxin.entity.goods.DiZiJuanGuiZei;
import com.yxld.yxchuangxin.entity.goods.MallNewOrder;
import com.yxld.yxchuangxin.entity.goods.ShopDianZiJuan;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for ConfirmOrderCodeActivity
 * @Description: $description
 * @date 2018/03/21 08:46:31
 */
public interface ConfirmOrderCodeContract {
    interface View extends BaseView<ConfirmOrderCodeContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 加载电子券规则
         *
         * @param entity
         */
        void onLoadOrderRemainDianZiQuanSucceed(DiZiJuanGuiZei entity);

        /**
         * 加载电子券成功
         *
         * @param dzq
         */
        void onLoadDianZiQuanSucceed(ShopDianZiJuan dzq);

        /**
         * 加载订单信息成功
         *
         * @param entity
         */
        void getOrderDetailSucceed(MallNewOrder entity);

        /**
         * 加载订单信息失败
         */
        void getOrderDetailFailed();

        /**
         * 提交订单成功
         */
        void confirmOrderSucceed(BaseEntityAll entity);
    }

    interface ConfirmOrderCodeContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        /**
         * 加载电子券
         */
        void loadDianZiQuan();

        /**
         * 加载电子券规则
         *
         * @param map
         */
        void loadOrderRemainDianZiQuan(Map<String, String> map);

        /**
         * 加载订单信息
         *
         * @param map
         */
        void getPosOrderDetail(Map<String, String> map);

        /**
         * 提交订单
         * @param map
         */

        void confirmOrder(Map<String, String> map);
    }
}