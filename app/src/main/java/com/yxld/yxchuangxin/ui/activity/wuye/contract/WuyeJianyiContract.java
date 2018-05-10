package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import android.content.Intent;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for WuyeJianyiFragment
 * @Description: $description
 * @date 2017/06/20 11:11:36
 */
public interface WuyeJianyiContract {
    interface View extends BaseView<WuyeJianyiContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 建议提交成功
         */
        void onJianYiBack();

        void onPickImgBack();

        void onUploadOVer(String url);
    }

    interface WuyeJianyiContractPresenter extends BasePresenter {
        /**
         *提交建议
         */
        void saveJianYi(Map map);

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