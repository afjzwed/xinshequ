package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author Yuan.Y.Q
 * @Package The contract for CommentListActivity
 * @Description: $description
 * @date 2017/06/29 11:01:08
 */
public interface CommentListContract {
    interface View extends BaseView<CommentListContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface CommentListContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}