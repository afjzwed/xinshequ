package com.yxld.yxchuangxin.ui.activity.camera.contract;

import com.yxld.yxchuangxin.entity.AppCameraList;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
import com.yxld.yxchuangxin.yoosee.FriendStatus;

import java.util.Map;

/**
 * @author hzp
 * @Package The contract for DeviceListFragment
 * @Description: $description
 * @date 2017/09/04 15:11:28
 */
public interface DeviceListContract {
    interface View extends BaseView<DeviceListContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 设置摄像头列表
         */
        void setCameraList(AppCameraList list);

        void deletOne(int position);

    }

    interface DeviceListContractPresenter extends BasePresenter {
        /**
         *登录技威
         */
        void Login();

        /**
         * 获取所有的摄像头
         */
        void getAllCamera();

        /**
         * 获取设备状态
         */
        void DeviceStatus(FriendStatus msg);

        /**
         * 删除摄像头
         */
        void deletCamera(Map map, int postion);
    }
}