package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.entity.CxwyMallComment;
import com.yxld.yxchuangxin.entity.MyAllComment;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author Yuan.Y.Q
 * @Package The contract for CommentListPartitionFragment
 * @Description: $description
 * @date 2017/06/30 16:43:22
 */
public interface CommentListPartitionContract {
    interface View extends BaseView<CommentListPartitionContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();


        void onLoadCommentSucceed(MyAllComment comments);
        void onLoadCommentFailed();
    }

    interface CommentListPartitionContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        void loadCommentFromServer(String productId,String type,int nextPage,int onePageSize);
    }
}