package com.yxld.yxchuangxin.ui.activity.rim.contract;

import com.yxld.yxchuangxin.entity.CxwyBusiness;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.List;
import java.util.Map;

/**
 * @author hu
 * @Package The contract for RimActivity
 * @Description: $description
 * @date 2017/06/12
 */
public interface RimContract {
    interface View extends BaseView<RimContractPresenter> {
        void setShangJiaList(CxwyBusiness business);
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface RimContractPresenter extends BasePresenter {
        void loadShangJiaList(Map<String, String> map);
    }
}