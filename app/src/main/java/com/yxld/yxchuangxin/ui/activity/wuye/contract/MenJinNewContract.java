package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for MenJinNewActivity
 * @Description: $description
 * @date 2018/05/26 11:11:04
 */
public interface MenJinNewContract {
    interface View extends BaseView<MenJinNewContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface MenJinNewContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}