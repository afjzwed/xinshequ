package com.yxld.yxchuangxin.ui.activity.rim.contract;

import com.yxld.yxchuangxin.entity.RimOrderCommentBean;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author wwx
 * @Package The contract for RimCommentListActivity
 * @Description: $description
 * @date 2017/06/17
 */
public interface RimCommentListContract {
    interface View extends BaseView<RimCommentListContractPresenter> {
        void setData(boolean isRefresh,RimOrderCommentBean data);
        void setError();
        void setEmptyData(RimOrderCommentBean data);

        void showProgressDialog();

        void closeProgressDialog();

    }

    interface RimCommentListContractPresenter extends BasePresenter {
        void getRimCommentList(Map map, boolean isRefresh);
    }
}