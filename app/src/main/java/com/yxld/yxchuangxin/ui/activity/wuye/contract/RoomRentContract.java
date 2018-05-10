package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.entity.RoomRent;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for RoomRentActivity
 * @Description: $description
 * @date 2017/06/16
 */
public interface RoomRentContract {
    interface View extends BaseView<RoomRentContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 设置房屋列表
         */
        void setRentList(RoomRent roomRent);
    }

    interface RoomRentContractPresenter extends BasePresenter {
        /**
         * 获取房屋出租列表
         */
        void getRentList();
        /**
         * 更新房屋出租状态
         */
        void updateRentStatus(Map map);
    }
}