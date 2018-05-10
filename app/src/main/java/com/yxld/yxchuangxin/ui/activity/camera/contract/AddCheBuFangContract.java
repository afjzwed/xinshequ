package com.yxld.yxchuangxin.ui.activity.camera.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hzp
 * @Package The contract for AddCheBuFangActivity
 * @Description: $description
 * @date 2017/09/05 18:10:45
 */
public interface AddCheBuFangContract {
    interface View extends BaseView<AddCheBuFangContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void addSuccesse(String content);
    }

    interface AddCheBuFangContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        void saveBufang(Map map);
    }
}