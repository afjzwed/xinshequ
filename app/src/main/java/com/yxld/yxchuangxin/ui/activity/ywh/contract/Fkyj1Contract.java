package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.entity.YwhFkyj;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for Fkyj1Fragment
 * @Description: $description
 * @date 2018/11/09 13:26:28
 */
public interface Fkyj1Contract {
    interface View extends BaseView<Fkyj1ContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setData();

        void setError();
    }

    interface Fkyj1ContractPresenter extends BasePresenter {
        void conmitFkyjInfo1(Map map);
    }
}