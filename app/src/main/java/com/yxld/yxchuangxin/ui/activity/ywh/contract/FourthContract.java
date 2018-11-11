package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.DoorInfo;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author William
 * @Package The contract for FourthFragment
 * @Description: $description
 * @date 2018/11/08 11:20:19
 */
public interface FourthContract {
    interface View extends BaseView<FourthContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setFourthData(BaseEntity baseEntity);
    }

    interface FourthContractPresenter extends BasePresenter {
        void getFourthData();
    }
}