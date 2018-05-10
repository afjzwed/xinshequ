package com.yxld.yxchuangxin.ui.activity.mine.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for PatternCheckActivity
 * @Description: $description
 * @date 2018/04/04 14:08:08
 */
public interface PatternCheckContract {
    interface View extends BaseView<PatternCheckContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface PatternCheckContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}