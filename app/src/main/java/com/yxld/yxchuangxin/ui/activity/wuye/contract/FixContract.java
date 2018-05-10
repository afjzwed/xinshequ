package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import android.content.Intent;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author hu
 * @Package The contract for FixActivity
 * @Description: $description
 * @date 2017/06/15
 */
public interface FixContract {
    interface View extends BaseView<FixContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void onPickImgBack();

        /**
         * 提交结果到后台之后，清除一些数据。
         */
        void onUpLoadSuccess();
    }

    interface FixContractPresenter extends BasePresenter {
        void fromCameraUpLoad(int position);
        void fromAlbumUpLoad(int position);
        void onActivityResult(int requestCode, int resultCode, Intent data);
        void upLoadImg();

        /**
         * 初始化
         */
        void init();
    }
}