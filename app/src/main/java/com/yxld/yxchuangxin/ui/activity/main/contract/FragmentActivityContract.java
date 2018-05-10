package com.yxld.yxchuangxin.ui.activity.main.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

/**
 * @author hu
 * @Package The contract for FragmentActivityActivity
 * @Description: $description
 * @date 2017/05/18
 */
public interface FragmentActivityContract {
    interface View extends BaseView<FragmentActivityContractPresenter> {
        void setAdapter();
    }

    interface FragmentActivityContractPresenter extends BasePresenter {
    }
}