package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.YwhInfo;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author William
 * @Package The contract for FivethFragment
 * @Description: $description
 * @date 2018/11/08 14:11:35
 */
public interface FivethContract {
    interface View extends BaseView<FivethContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setFivethData(YwhInfo baseEntity);
    }

    interface FivethContractPresenter extends BasePresenter {
     void getFivethData(Map map);
    }
}