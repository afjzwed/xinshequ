package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.YwhMember;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author William
 * @Package The contract for YwhMemberShowActivity
 * @Description: $description
 * @date 2018/11/07 20:37:02
 */
public interface YwhMemberShowContract {
    interface View extends BaseView<YwhMemberShowContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setData(boolean isRefresh,YwhMember baseEntity);

        void setError();
    }

    interface YwhMemberShowContractPresenter extends BasePresenter {
        void getData(LinkedHashMap<String, String> map, boolean isRefresh);
    }
}