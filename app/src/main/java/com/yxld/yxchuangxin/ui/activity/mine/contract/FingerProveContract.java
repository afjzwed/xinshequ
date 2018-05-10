package com.yxld.yxchuangxin.ui.activity.mine.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for FingerProveActivity
 * @Description: $description
 * @date 2018/04/03 10:11:18
 */
public interface FingerProveContract {
    interface View extends BaseView<FingerProveContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface FingerProveContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}