package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.entity.StateOrderNum;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

/**
 * @author yuan
 * @Package The contract for MineFragment
 * @Description: $description
 * @date 2017/06/14
 */
public interface MineContract {
    interface View extends BaseView<MineContractPresenter> {
        void onLoadOrderStatusSucceed(StateOrderNum orderStatus);
        void onLoadOrderStatusFailed();
        void showProgressDialog();
        void closeProgressDialog();
    }

    interface MineContractPresenter extends BasePresenter {
        void loadOrderStatusFromServer();
    }
}