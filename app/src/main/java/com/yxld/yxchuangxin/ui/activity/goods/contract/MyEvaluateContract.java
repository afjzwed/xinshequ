package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.entity.MyAllComment;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author hzp
 * @Package The contract for MyEvaluateActivity
 * @Description: $description
 * @date 2017/10/23 13:55:27
 */
public interface MyEvaluateContract {
    interface View extends BaseView<MyEvaluateContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void onGetEvaluateSuccess(MyAllComment myAllComment);
    }

    interface MyEvaluateContractPresenter extends BasePresenter {
        void getMyEvaluate();
    }
}