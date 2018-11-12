package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.entity.YwhInfo;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for TwoFragment
 * @Description: $description
 * @date 2018/11/08 09:44:57
 */
public interface TwoContract {
    interface View extends BaseView<TwoContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void getDataSuccess(YwhInfo baseEntity);
    }

    interface TwoContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
void getData(Map map);
    }
}