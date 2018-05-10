package com.yxld.yxchuangxin.ui.activity.rim.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author wwx
 * @Package The contract for RimCommentAddActivity
 * @Description: $description
 * @date 2017/06/17
 */
public interface RimCommentAddContract {
    interface View extends BaseView<RimCommentAddContractPresenter> {
       void requestSuccess();
       void requestFail(String msg);
    }

    interface RimCommentAddContractPresenter extends BasePresenter {
        void addCommentData(Map map);
    }
}