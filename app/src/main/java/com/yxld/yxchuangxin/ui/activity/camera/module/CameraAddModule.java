package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.CameraAddActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.CameraAddContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.CameraAddPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of CameraAddActivity, provide field for CameraAddActivity
 * @date 2017/06/21 10:21:55
 */
@Module
public class CameraAddModule {
    private final CameraAddContract.View mView;


    public CameraAddModule(CameraAddContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CameraAddPresenter provideCameraAddPresenter(HttpAPIWrapper httpAPIWrapper, CameraAddActivity mActivity) {
        return new CameraAddPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public CameraAddActivity provideCameraAddActivity() {
        return (CameraAddActivity) mView;
    }
}