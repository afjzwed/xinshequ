package com.yxld.yxchuangxin.ui.activity.rim.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author wwx
 * @Package The contract for RimXinZhouBianFragment
 * @Description: $description
 * @date 2017/06/16
 */
public interface RimXinZhouBianContract {
    interface View extends BaseView<RimXinZhouBianContractPresenter> {
        void initView();
        void selectView();
    }

    interface RimXinZhouBianContractPresenter extends BasePresenter {
    }
}