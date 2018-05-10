package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.entity.CxwyBaoxiu;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for FixInFragment
 * @Description: $description
 * @date 2017/06/15
 */
public interface FixInContract {
    interface View extends BaseView<FixInContractPresenter> {
        /**
         *
         * 设置列表
         */
        void setAdapter(CxwyBaoxiu baoxiu);
    }

    interface FixInContractPresenter extends BasePresenter {
        /**
         * h获取报修列表
         * @param map
         */
        void getRepairAllList(Map map);
        /**
         * h获取报修列表
         * @param map
         */
        void getRepairOtherList(Map map);
    }
}