package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import android.content.Intent;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for WuyeTousuFragment
 * @Description: $description
 * @date 2017/06/20 11:11:21
 */
public interface WuyeTousuContract {
    interface View extends BaseView<WuyeTousuContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 投诉返回
         */
        void onTousuBack();

        void onPickImgBack();

        void onUploadOVer(String url);
    }

    interface WuyeTousuContractPresenter extends BasePresenter {
        /**
         *
         */
        void saveTousu(Map map);

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