package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.entity.AppYezhuFangwu;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for LiveMemberActivity
 * @Description: $description
 * @date 2017/06/07
 */
public interface LiveMemberContract {
    interface View extends BaseView<LiveMemberContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 设置入住成员
         * @param data
         */
        void setMember(AppYezhuFangwu data);

        /**
         * 删除成员
         */
        void deletMember(int position);
    }

    interface LiveMemberContractPresenter extends BasePresenter {
        void getAllLiveMember(Map map);
        /**
         * 删除某个入住的成员
         */
        void deletLiveMember(int position);
    }
}