package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.entity.Accredit;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for FangxingActivity
 * @Description: $description
 * @date 2017/06/13
 */
public interface FangxingContract {
    interface View extends BaseView<FangxingContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 设置adapter 列表
         */
        void setAdapter(Accredit accredit);
    }

    interface FangxingContractPresenter extends BasePresenter {
        /**
         * 业主获取授权放行列表
         * @param map
         */
        void getAccreditListProprietor(Map map);

        /**
         * 租客获取授权放行列表
         * @param map
         */
        void getAccreditListRent(Map map);

        /**
         * 获取授权放行的详情
         */
        void getAccreditDetail(Map map);
    }
}