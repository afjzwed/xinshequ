package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import android.content.Intent;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author William
 * @Package The contract for NewComplainActivity
 * @Description: $description
 * @date 2018/12/14 10:49:02
 */
public interface NewComplainContract {
    interface View extends BaseView<NewComplainContractPresenter> {
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

    interface NewComplainContractPresenter extends BasePresenter {
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