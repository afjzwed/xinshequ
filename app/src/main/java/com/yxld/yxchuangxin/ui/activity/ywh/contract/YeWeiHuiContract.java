package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.YwhCurrentflow;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author William
 * @Package The contract for YeWeiHuiActivity
 * @Description: $description
 * @date 2018/11/07 11:49:57
 */
public interface YeWeiHuiContract {
    interface View extends BaseView<YeWeiHuiContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setData(YwhCurrentflow baseEntity);
    }

    interface YeWeiHuiContractPresenter extends BasePresenter {
        void getData();
    }
}