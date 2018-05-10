package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.CameraConfigActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.CameraConfigContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.CameraConfigPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of CameraConfigActivity, provide field for CameraConfigActivity
 * @date 2017/06/21 10:21:40
 */
@Module
public class CameraConfigModule {
    private final CameraConfigContract.View mView;


    public CameraConfigModule(CameraConfigContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CameraConfigPresenter provideCameraConfigPresenter(HttpAPIWrapper httpAPIWrapper, CameraConfigActivity mActivity) {
        return new CameraConfigPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public CameraConfigActivity provideCameraConfigActivity() {
        return (CameraConfigActivity) mView;
    }
}