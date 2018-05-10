package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author Yuan.Y.Q
 * @Package The contract for CommentAndShowOrderActivity
 * @Description: $description
 * @date 2017/06/28 20:14:36
 */
public interface CommentAndShowOrderContract {
    interface View extends BaseView<CommentAndShowOrderContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void onCommentResponse(BaseEntity entity);
        void onCommentFailed();
    }

    interface CommentAndShowOrderContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void addComment(Map<String,String> params);
    }
}