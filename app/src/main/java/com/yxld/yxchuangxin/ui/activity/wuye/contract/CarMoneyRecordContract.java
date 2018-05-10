package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.entity.CarJiaofeiRecord;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for CarMoneyRecordActivity
 * @Description: $description
 * @date 2017/07/06 18:00:14
 */
public interface CarMoneyRecordContract {
    interface View extends BaseView<CarMoneyRecordContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setList(CarJiaofeiRecord record);
    }

    interface CarMoneyRecordContractPresenter extends BasePresenter {
        /**
         *
         */
        void getCarMoneyRecord(Map map);
    }
}