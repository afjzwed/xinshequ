package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.CameraInformActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.CameraInformContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.CameraInformPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of CameraInformActivity, provide field for CameraInformActivity
 * @date 2017/10/18 09:18:09
 */
@Module
public class CameraInformModule {
    private final CameraInformContract.View mView;


    public CameraInformModule(CameraInformContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CameraInformPresenter provideCameraInformPresenter(HttpAPIWrapper httpAPIWrapper, CameraInformActivity mActivity) {
        return new CameraInformPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public CameraInformActivity provideCameraInformActivity() {
        return (CameraInformActivity) mView;
    }
}