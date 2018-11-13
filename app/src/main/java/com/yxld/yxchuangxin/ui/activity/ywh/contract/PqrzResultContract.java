package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.YwhHouse;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for PqrzResultActivity
 * @Description: $description
 * @date 2018/11/09 14:52:55
 */
public interface PqrzResultContract {
    interface View extends BaseView<PqrzResultContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void getDetailSuccess(YwhHouse baseEntity);
        void getStatusSuccess(BaseEntity baseEntity);
    }

    interface PqrzResultContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getDetail();
        void getChangeStatus();
    }
}