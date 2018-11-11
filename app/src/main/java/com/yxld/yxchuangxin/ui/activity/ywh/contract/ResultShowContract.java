package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author William
 * @Package The contract for ResultShowActivity
 * @Description: $description
 * @date 2018/11/07 20:09:47
 */
public interface ResultShowContract {
    interface View extends BaseView<ResultShowContractPresenter> {
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

    interface ResultShowContractPresenter extends BasePresenter {
        void getData(Map map);
    }
}