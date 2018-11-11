package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author William
 * @Package The contract for CheckNoticeActivity
 * @Description: $description
 * @date 2018/11/08 17:11:57
 */
public interface CheckNoticeContract {
    interface View extends BaseView<CheckNoticeContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setData(BaseEntity baseEntity);
    }

    interface CheckNoticeContractPresenter extends BasePresenter {
        void getData(Map map);
    }
}