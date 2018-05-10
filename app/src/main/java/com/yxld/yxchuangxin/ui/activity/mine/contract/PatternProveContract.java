package com.yxld.yxchuangxin.ui.activity.mine.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for PatternProveActivity
 * @Description: $description
 * @date 2018/04/04 11:25:15
 */
public interface PatternProveContract {
    interface View extends BaseView<PatternProveContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface PatternProveContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}