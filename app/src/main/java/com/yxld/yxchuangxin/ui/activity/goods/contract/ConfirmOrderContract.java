package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.IsNight;
import com.yxld.yxchuangxin.entity.OrderRemainDianZiQuanEntity;
import com.yxld.yxchuangxin.entity.YezhuDainZhiQuan;
import com.yxld.yxchuangxin.entity.goods.BaseEntityAll;
import com.yxld.yxchuangxin.entity.goods.DiZiJuanGuiZei;
import com.yxld.yxchuangxin.entity.goods.ShopDianZiJuan;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author Yuan.Y.Q
 * @Package The contract for ConfirmOrderActivity
 * @Description: $description
 * @date 2017/06/22 11:07:51
 */
public interface ConfirmOrderContract {
    interface View extends BaseView<ConfirmOrderContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void onLoadDianZiQuanSucceed(ShopDianZiJuan dzq);

        void onLoadOrderRemainDianZiQuanSucceed(DiZiJuanGuiZei entity);

        void onAddOrderSuccess(OrderRemainDianZiQuanEntity entity);

        void onLoadDataFailed(String msg);

        void onLoadDataFailed();
        void setIsNight(IsNight isNight);
        void onLoadOrderSuccess(BaseEntityAll baseEntityAll);
    }

    interface ConfirmOrderContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void loadDianZiQuan();

        void loadOrderRemainDianZiQuan(Map<String,String> map);

        void addMallOrder(Map<String, String> params);
        void isNight();
        void getOrder(String bianhao);

    }
}