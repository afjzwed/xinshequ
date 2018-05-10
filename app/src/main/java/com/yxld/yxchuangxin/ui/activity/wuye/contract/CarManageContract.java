package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.entity.CarList;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for CarManageActivity
 * @Description: $description
 * @date 2017/06/08
 */
public interface CarManageContract {
    interface View extends BaseView<CarManageContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 设置车辆列表
         */
        void setCarList(CarList carList);

        /**
         * 锁定或者解锁车辆返回
         */
        void onLocakBack();
    }

    interface CarManageContractPresenter extends BasePresenter {
        void getCarList(Map map);

        /**
         * 解锁和锁定控制
         */
        void lockControl(Map map);
    }
}