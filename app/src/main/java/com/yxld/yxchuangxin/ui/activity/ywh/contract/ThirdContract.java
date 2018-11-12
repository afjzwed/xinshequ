package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.entity.YwhInfo;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for ThirdFragment
 * @Description: $description
 * @date 2018/11/08 09:53:49
 */
public interface ThirdContract {
    interface View extends BaseView<ThirdContractPresenter> {
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

    interface ThirdContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
void getData(Map map);
    }
}