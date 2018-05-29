package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.DoorInfo;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for MenJinListFragment
 * @Description: $description
 * @date 2018/05/26 13:35:55
 */
public interface MenJinListContract {
    interface View extends BaseView<MenJinListContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
        void setDoorList(DoorInfo baseEntity);
    }

    interface MenJinListContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getDoorList();
    }
}