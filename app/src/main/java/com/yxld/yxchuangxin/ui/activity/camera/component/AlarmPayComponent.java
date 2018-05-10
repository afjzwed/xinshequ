package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.AlarmPayActivity;
import com.yxld.yxchuangxin.ui.activity.camera.module.AlarmPayModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for AlarmPayActivity
 * @date 2017/09/28 09:59:31
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = AlarmPayModule.class)
public interface AlarmPayComponent {
    AlarmPayActivity inject(AlarmPayActivity Activity);
}