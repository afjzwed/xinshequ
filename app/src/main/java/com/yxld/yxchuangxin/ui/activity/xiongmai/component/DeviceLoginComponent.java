package com.yxld.yxchuangxin.ui.activity.xiongmai.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.xiongmai.DeviceLoginActivity;
import com.yxld.yxchuangxin.ui.activity.xiongmai.module.DeviceLoginModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.xiongmai
 * @Description: The component for DeviceLoginActivity
 * @date 2017/07/18 09:30:54
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = DeviceLoginModule.class)
public interface DeviceLoginComponent {
    DeviceLoginActivity inject(DeviceLoginActivity Activity);
}