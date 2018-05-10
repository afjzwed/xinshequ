package com.yxld.yxchuangxin.ui.activity.rim.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author wwx
 * @Package The contract for RimMineFragment
 * @Description: $description
 * @date 2017/06/16
 */
public interface RimMineContract {
    interface View extends BaseView<RimMineContractPresenter> {
        void loadViewForCode();
        void initView();
    }

    interface RimMineContractPresenter extends BasePresenter {
    }
}