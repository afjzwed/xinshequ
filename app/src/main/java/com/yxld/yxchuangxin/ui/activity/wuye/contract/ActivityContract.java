package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.entity.CxwyMessage;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for ActivityFragment
 * @Description: $description
 * @date 2017/06/14
 */
public interface ActivityContract {
    interface View extends BaseView<ActivityContractPresenter> {
        void setActivity(CxwyMessage message);
    }

    interface ActivityContractPresenter extends BasePresenter {
        /**
         * 获取活动
         * @param map
         */
        void getActivity(Map map);
    }
}