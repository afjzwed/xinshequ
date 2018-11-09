package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for Fkyj2Fragment
 * @Description: $description
 * @date 2018/11/09 13:42:26
 */
public interface Fkyj2Contract {
    interface View extends BaseView<Fkyj2ContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface Fkyj2ContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}