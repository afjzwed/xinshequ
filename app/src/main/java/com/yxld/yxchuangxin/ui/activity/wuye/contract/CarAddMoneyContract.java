package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for CarAddMoneyActivity
 * @Description: $description
 * @date 2017/07/06 15:05:41
 */
public interface CarAddMoneyContract {
    interface View extends BaseView<CarAddMoneyContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface CarAddMoneyContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        /**
         * 车辆缴费
         */
        void affordMoney(Map map);

        /**
         * 计算缴费后到期时间
         */
        long caculateTime(int month, String startTime);
    }
}