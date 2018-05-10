package com.yxld.yxchuangxin.ui.activity.common.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author hu
 * @Package The contract for RealPlayActivity
 * @Description: $description
 * @date 2017/06/08
 */
public interface RealPlayContract {
    interface View extends BaseView<RealPlayContractPresenter> {
    }

    interface RealPlayContractPresenter extends BasePresenter {
    }
}