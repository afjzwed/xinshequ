package com.yxld.yxchuangxin.ui.activity.camera.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hzp
 * @Package The contract for ModifyFangquActivity
 * @Description: $description
 * @date 2017/09/19 17:43:45
 */
public interface ModifyFangquContract {
    interface View extends BaseView<ModifyFangquContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface ModifyFangquContractPresenter extends BasePresenter {
        void updateFangqu(Map map);
    }
}