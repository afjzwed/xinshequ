package com.yxld.yxchuangxin.ui.activity.rim.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author wwx
 * @Package The contract for RimComplainAddActivity
 * @Description: $description
 * @date 2017/06/17
 */
public interface RimComplainAddContract {
    interface View extends BaseView<RimComplainAddContractPresenter> {
        void initPopWindow();
        void requestSuccess();
        void showProgressDialog();
        void closeProgressDialog();
        void  onError();
    }

    interface RimComplainAddContractPresenter extends BasePresenter {
        void addComplanData(Map map);
    }
}