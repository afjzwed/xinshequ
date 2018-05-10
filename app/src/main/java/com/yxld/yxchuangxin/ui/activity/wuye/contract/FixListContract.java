package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for FixListActivity
 * @Description: $description
 * @date 2017/06/15
 */
public interface FixListContract {
    interface View extends BaseView<FixListContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface FixListContractPresenter extends BasePresenter {
        /**
         * 获取所有的报修
         * @param map
         */
        void getRepairAllList(Map map);
    }
}