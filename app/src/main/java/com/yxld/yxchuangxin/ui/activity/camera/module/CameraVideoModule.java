package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.CameraVideoActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.CameraVideoContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.CameraVideoPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of CameraVideoActivity, provide field for CameraVideoActivity
 * @date 2017/06/21 10:22:39
 */
@Module
public class CameraVideoModule {
    private final CameraVideoContract.View mView;


    public CameraVideoModule(CameraVideoContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CameraVideoPresenter provideCameraVideoPresenter(HttpAPIWrapper httpAPIWrapper, CameraVideoActivity mActivity) {
        return new CameraVideoPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public CameraVideoActivity provideCameraVideoActivity() {
        return (CameraVideoActivity) mView;
    }
}