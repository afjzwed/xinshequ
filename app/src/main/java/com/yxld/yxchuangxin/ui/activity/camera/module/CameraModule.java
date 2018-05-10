package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.CameraActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.CameraContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.CameraPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of CameraActivity, provide field for CameraActivity
 * @date 2017/06/21 10:19:03
 */
@Module
public class CameraModule {
    private final CameraContract.View mView;


    public CameraModule(CameraContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CameraPresenter provideCameraPresenter(HttpAPIWrapper httpAPIWrapper, CameraActivity mActivity) {
        return new CameraPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public CameraActivity provideCameraActivity() {
        return (CameraActivity) mView;
    }
}