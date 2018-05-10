package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.entity.JiaofeiMingxi;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for JiaofeiListActivity
 * @Description: $description
 * @date 2017/07/01 13:34:37
 */
public interface JiaofeiListContract {
    interface View extends BaseView<JiaofeiListContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setList(JiaofeiMingxi mingxi);

        void setMoreList();
    }

    interface JiaofeiListContractPresenter extends BasePresenter {
        /**
         *
         */
        void getList(Map map);
    }
}