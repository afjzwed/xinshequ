package com.yxld.yxchuangxin.ui.activity.camera.contract;

import com.yxld.yxchuangxin.entity.XuFeiBean;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for AlarmPayActivity
 * @Description: $description
 * @date 2017/09/28 09:59:31
 */
public interface AlarmPayContract {
    interface View extends BaseView<CameraPayContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setData(XuFeiBean s);

        void setOrder(String order);
    }

    interface CameraPayContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        /**
         * 获取缴费内容信息
         *
         * @param map
         */
        void getData(Map<String, String> map);

        /**
         * 获取订单号
         *
         * @param map
         */
        void getOrder(Map<String, String> map);
    }
}