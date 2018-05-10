package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.DeviceListFragment;
import com.yxld.yxchuangxin.ui.activity.camera.module.DeviceListModule;

import dagger.Component;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for DeviceListFragment
 * @date 2017/09/04 15:11:28
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = DeviceListModule.class)
public interface DeviceListComponent {
    DeviceListFragment inject(DeviceListFragment Fragment);
}