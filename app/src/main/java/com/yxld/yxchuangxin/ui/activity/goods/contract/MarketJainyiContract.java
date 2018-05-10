package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for MarketJainyiActivity
 * @Description: $description
 * @date 2017/06/22 16:19:40
 */
public interface MarketJainyiContract {
    interface View extends BaseView<MarketJainyiContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void onJianYiBack(BaseEntity baseEntity);
    }

    interface MarketJainyiContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        /**
         * 保存建议
         * @param map
         */
        void saveJianYi(Map map);
    }
}