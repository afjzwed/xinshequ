package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.AlarmActivity;
import com.yxld.yxchuangxin.ui.activity.camera.module.AlarmModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for AlarmActivity
 * @date 2017/06/21 10:23:03
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = AlarmModule.class)
public interface AlarmComponent {
    AlarmActivity inject(AlarmActivity Activity);
}