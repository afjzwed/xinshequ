package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.SJOrder;
import com.yxld.yxchuangxin.entity.YwhCurrentflow;
import com.yxld.yxchuangxin.entity.YwhFkyj;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xlei
 * @Package The contract for Fkyj2Fragment
 * @Description: $description
 * @date 2018/11/09 13:42:26
 */
public interface Fkyj2Contract {
    interface View extends BaseView<Fkyj2ContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setData(boolean isRefresh,YwhFkyj baseEntity);

        void setError();

        void setData2(YwhFkyj baseEntity);
    }

    interface Fkyj2ContractPresenter extends BasePresenter {
        void getData1(LinkedHashMap<String, String> map, boolean isRefresh);

        void getData3(LinkedHashMap<String, String> map, boolean isRefresh);

        void getData2(Map map);
    }
}