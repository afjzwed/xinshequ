package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.AppCameraList;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.DeviceActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.DeviceContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.DevicePresenter;
import com.yxld.yxchuangxin.ui.adapter.camera.CameraListAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of DeviceActivity, provide field for DeviceActivity
 * @date 2017/06/20 17:26:32
 */
@Module
public class DeviceModule {
    private final DeviceContract.View mView;


    public DeviceModule(DeviceContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public DevicePresenter provideDevicePresenter(HttpAPIWrapper httpAPIWrapper, DeviceActivity mActivity) {
        return new DevicePresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public DeviceActivity provideDeviceActivity() {
        return (DeviceActivity) mView;
    }

    @Provides
    @ActivityScope
    public List<AppCameraList> provideList() {
        List<AppCameraList> list = new ArrayList<>();
        return list;
    }

    @Provides
    @ActivityScope
    public CameraListAdapter provideCameraListAdapter(List<AppCameraList> list) {
        return new CameraListAdapter(list);
    }
}