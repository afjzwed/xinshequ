package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.entity.AccreditDetail;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for FangxingDetaillActivity
 * @Description: $description
 * @date 2017/06/14
 */
public interface FangxingDetaillContract {
    interface View extends BaseView<FangxingDetaillContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 设置列表
         */
        void setList(AccreditDetail detail);

        /**
         * 授权放行通过
         */
        void onPassAccredit();
    }

    interface FangxingDetaillContractPresenter extends BasePresenter {
        /**
         * 获取放行详情
         * @param map
         */
        void getFangxingDetail(Map map);

        /**
         * 业主通过授权放行
         */
        void passAccredit(Map map);
    }
}