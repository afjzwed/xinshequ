package com.yxld.yxchuangxin.ui.activity.xiongmai.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.xiongmai.DeviceLoginActivity;
import com.yxld.yxchuangxin.ui.activity.xiongmai.contract.DeviceLoginContract;
import com.yxld.yxchuangxin.ui.activity.xiongmai.presenter.DeviceLoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.xiongmai
 * @Description: The moduele of DeviceLoginActivity, provide field for DeviceLoginActivity
 * @date 2017/07/18 09:30:54
 */
@Module
public class DeviceLoginModule {
    private final DeviceLoginContract.View mView;


    public DeviceLoginModule(DeviceLoginContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public DeviceLoginPresenter provideDeviceLoginPresenter(HttpAPIWrapper httpAPIWrapper, DeviceLoginActivity mActivity) {
        return new DeviceLoginPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public DeviceLoginActivity provideDeviceLoginActivity() {
        return (DeviceLoginActivity) mView;
    }
}