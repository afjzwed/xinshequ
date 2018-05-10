package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.DeviceActivity;
import com.yxld.yxchuangxin.ui.activity.camera.module.DeviceModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for DeviceActivity
 * @date 2017/06/20 17:26:32
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = DeviceModule.class)
public interface DeviceComponent {
    DeviceActivity inject(DeviceActivity Activity);
}