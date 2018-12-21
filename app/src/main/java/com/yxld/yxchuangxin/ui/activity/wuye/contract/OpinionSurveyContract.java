package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.OpinionSurveyEntity;
import com.yxld.yxchuangxin.entity.YwhMember;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.LinkedHashMap;

/**
 * @author William
 * @Package The contract for OpinionSurveyActivity
 * @Description: $description
 * @date 2018/11/12 18:08:34
 */
public interface OpinionSurveyContract {
    interface View extends BaseView<OpinionSurveyContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setData(boolean isRefresh,OpinionSurveyEntity baseEntity);

        void setError(String msg);
    }

    interface OpinionSurveyContractPresenter extends BasePresenter {
        void getData(LinkedHashMap<String, String> map, boolean isRefresh);
    }
}