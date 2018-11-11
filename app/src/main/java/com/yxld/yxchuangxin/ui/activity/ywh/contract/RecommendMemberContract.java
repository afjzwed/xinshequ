package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author William
 * @Package The contract for RecommendMemberActivity
 * @Description: $description
 * @date 2018/11/07 19:36:36
 */
public interface RecommendMemberContract {
    interface View extends BaseView<RecommendMemberContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setData(BaseEntity baseEntity);
    }

    interface RecommendMemberContractPresenter extends BasePresenter {
        void getData(Map map);
    }
}