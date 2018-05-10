package com.yxld.yxchuangxin.ui.activity.rim.contract;

import com.yxld.yxchuangxin.entity.SJOrderStatus;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author wwx
 * @Package The contract for RimOrderDynamicActivity
 * @Description: $description
 * @date 2017/06/17
 */
public interface RimOrderDynamicContract {
    interface View extends BaseView<RimOrderDynamicContractPresenter> {
        void requestSuccess(SJOrderStatus listData);
        void requestError(String msg);

        void showProgressDialog();

        void closeProgressDialog();

    }

    interface RimOrderDynamicContractPresenter extends BasePresenter {
        void requestDynamicData(Map map);
    }
}