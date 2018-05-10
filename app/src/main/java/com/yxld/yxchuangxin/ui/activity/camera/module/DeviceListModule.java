package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.AppCameraList;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.DeviceListFragment;
import com.yxld.yxchuangxin.ui.activity.camera.contract.DeviceListContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.DeviceListPresenter;
import com.yxld.yxchuangxin.ui.adapter.camera.CameraListAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of DeviceListFragment, provide field for DeviceListFragment
 * @date 2017/09/04 15:11:28
 */
@Module
public class DeviceListModule {
    private final DeviceListContract.View mView;


    public DeviceListModule(DeviceListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public DeviceListPresenter provideDeviceListPresenter(HttpAPIWrapper httpAPIWrapper, DeviceListFragment mFragment) {
        return new DeviceListPresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public DeviceListFragment provideDeviceListFragment() {
        return (DeviceListFragment) mView;
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