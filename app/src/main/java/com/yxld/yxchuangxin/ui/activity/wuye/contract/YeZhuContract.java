package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.entity.OpenDoorCode;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for YeZhuFragment
 * @Description: $description
 * @date 2017/06/06
 */
public interface YeZhuContract {
    interface View extends BaseView<YeZhuContractPresenter> {
        /**
         * 生成二维码
         *
         * @param openDoorCode
         */
        void creatQRcode(OpenDoorCode openDoorCode);

        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

    }

    interface YeZhuContractPresenter extends BasePresenter {
        void getQRCodeInfo(Map map);
    }
}