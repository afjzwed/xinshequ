package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.YwhInfo;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for OneFragment
 * @Description: $description
 * @date 2018/11/08 08:54:54
 */
public interface OneContract {
    interface View extends BaseView<OneContractPresenter> {
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

    interface OneContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getData(Map map);
    }
}