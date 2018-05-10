package com.yxld.yxchuangxin.ui.activity.goods.contract;

import android.content.Intent;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for GoodsSaleActivity
 * @Description: $description
 * @date 2017/10/23 10:36:05
 */
public interface GoodsSaleContract {
    interface View extends BaseView<GoodsSaleContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void onOneCheckBoxNotChecked();

        void onAllChecked(boolean allChecked);

        void onPickImgBack();

        /**
         * 七牛上传图片成功后提交数据给服务器
         */
        void onUpLoadSuccess(String uploadImgUrl);
        void postShouhouSuccess();
    }

    interface GoodsSaleContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void fromCameraUpLoad(int position);

        void fromAlbumUpLoad(int position);

        void onActivityResult(int requestCode, int resultCode, Intent data);

        void upLoadImg();

        /**
         * 初始化
         */
        void init();

        /**
         * 提交数据到服务器
         *
         * @param map
         */
        void postShouhou(Map<String, String> map);

    }
}