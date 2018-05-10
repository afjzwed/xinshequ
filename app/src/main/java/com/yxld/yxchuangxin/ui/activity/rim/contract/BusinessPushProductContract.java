package com.yxld.yxchuangxin.ui.activity.rim.contract;

import com.yxld.yxchuangxin.db.green.SPInfo;
import com.yxld.yxchuangxin.entity.CxwyProductInfo;
import com.yxld.yxchuangxin.entity.PushOrder;
import com.yxld.yxchuangxin.entity.RimActivityDiscount;
import com.yxld.yxchuangxin.entity.ShopCarList;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author hu
 * @Package The contract for BusinessPushProductActivity
 * @Description: $description
 * @date 2017/06/19 11:24:46
 */
public interface BusinessPushProductContract {
    interface View extends BaseView<BusinessPushProductContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
        /**
         * 更新地址信息
         */
        void upDateAddress(String name, String shouji, String roomInfo);
        /**
         * 获取信息之后，更新ui
         * @param chooseClassifyList
         */
        void upDateBusinessInfoAndProduct(List<ShopCarList.ShopCarBean> chooseClassifyList);

        /**
         * 设置产品的数量和价格
         */
        void setProductInfo(String count, String price, String businessName);

        /**
         * 设置折扣信息
         * @param data
         */
        void setDiscountInfo(RimActivityDiscount data);

        /**
         * 提交订单成功
         * @param data
         */
        void commitOrdSuccess(PushOrder data);
    }

    interface BusinessPushProductContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        /**
         * 获取地址信息
         */
        void getAddress();
        /**
         * 获取商品信息和商家信息
         */
        void getBusinessInfoAndProduct(String num, List<ShopCarList.ShopCarBean> list);

        /**
         * 提交订单信息
         */
        void upLoadOrderInfo(Map<String,String> map);
    }
}