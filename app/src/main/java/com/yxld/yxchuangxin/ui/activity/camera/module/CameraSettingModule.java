package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.CameraSettingActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.CameraSettingContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.CameraSettingPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of CameraSettingActivity, provide field for CameraSettingActivity
 * @date 2017/06/21 10:21:20
 */
@Module
public class CameraSettingModule {
    private final CameraSettingContract.View mView;


    public CameraSettingModule(CameraSettingContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CameraSettingPresenter provideCameraSettingPresenter(HttpAPIWrapper httpAPIWrapper, CameraSettingActivity mActivity) {
        return new CameraSettingPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public CameraSettingActivity provideCameraSettingActivity() {
        return (CameraSettingActivity) mView;
    }
}