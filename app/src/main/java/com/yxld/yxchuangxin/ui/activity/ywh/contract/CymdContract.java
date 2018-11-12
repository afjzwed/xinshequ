package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for CymdActivity
 * @Description: $description
 * @date 2018/11/09 09:18:52
 */
public interface CymdContract {
    interface View extends BaseView<CymdContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void getListSuccess(BaseEntity baseEntity);
    }

    interface CymdContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getList();
    }
}