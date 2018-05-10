package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.entity.CxwyComplain;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for ComplainListActivity
 * @Description: $description
 * @date 2017/06/20 13:31:40
 */
public interface ComplainListContract {
    interface View extends BaseView<ComplainListContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 设置投诉列表
         */
        void setComplainList(CxwyComplain complainList);
    }

    interface ComplainListContractPresenter extends BasePresenter {
        /**
         *获取投诉列表
         */
        void getComplainList(Map map);
    }
}