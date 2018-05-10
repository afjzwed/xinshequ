package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for ArrearageFragment
 * @Description: $description
 * @date 2017/07/01 13:46:50
 */
public interface ArrearageContract {
    interface View extends BaseView<ArrearageContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setList();

        void setMoreList();
    }

    interface ArrearageContractPresenter extends BasePresenter {
        /**
         *
         */
        void getList(Map map);
    }
}