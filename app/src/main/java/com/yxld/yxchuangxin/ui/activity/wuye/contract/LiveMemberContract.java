package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import android.content.Intent;

import com.yxld.yxchuangxin.entity.AppYezhuFangwu;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for LiveMemberActivity
 * @Description: $description
 * @date 2017/06/07
 */
public interface LiveMemberContract {
    interface View extends BaseView<LiveMemberContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 设置入住成员
         * @param data
         */
        void setMember(AppYezhuFangwu data);

        /**
         * 删除成员
         */
        void deletMember(int position);

        /**
         * 图片上传成功后调用接口
         * @param url
         */
        void onUploadOVer(String url);

        /**
         * 成功返回
         */
        void onUpFaceBack(boolean isUp);
    }

    interface LiveMemberContractPresenter extends BasePresenter {
        void getAllLiveMember(Map map);
        /**
         * 删除某个入住的成员
         */
        void deletLiveMember(int position);

        void upFace(Map map);

        //拍照上传
        void fromCameraUpLoad();
        //从相册上传
        void fromAlbumUpLoad();
        void onActivityResult(int requestCode, int resultCode, Intent data);
        //上传图片到七牛
        void upLoadImg(String path);

        /**
         * 初始化
         */
        void init();
    }
}