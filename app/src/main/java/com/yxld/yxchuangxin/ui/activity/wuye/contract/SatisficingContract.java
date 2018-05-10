package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.entity.Question;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.List;

/**
 * @author hu
 * @Package The contract for SatisficingActivity
 * @Description: $description
 * @date 2017/06/20 10:07:42
 */
public interface SatisficingContract {
    interface View extends BaseView<SatisficingContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setData(List<Question.DataBean> list);

        /**
         * 提交成功
         */
        void onSuccess();
    }

    interface SatisficingContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        /**
         * 获取满意度调查的问题
         */
        void getQuestion();

        /**
         * 提交满意度答案
         */
        void saveMaYiDu(String map);
    }
}