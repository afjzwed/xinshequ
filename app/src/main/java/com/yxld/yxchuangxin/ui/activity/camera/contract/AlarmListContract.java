package com.yxld.yxchuangxin.ui.activity.camera.contract;

import com.yxld.yxchuangxin.entity.HostEntiti;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.List;
import java.util.Map;

/**
 * @author hzp
 * @Package The contract for AlarmListFragment
 * @Description: $description
 * @date 2017/09/04 15:09:38
 */
public interface AlarmListContract {
    interface View extends BaseView<AlarmListContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setAdapter(List<HostEntiti.DataBean> list);
    }

    interface AlarmListContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        void getHost();

        void buCheFang(Map map);

        void mingDi(Map map);
    }
}