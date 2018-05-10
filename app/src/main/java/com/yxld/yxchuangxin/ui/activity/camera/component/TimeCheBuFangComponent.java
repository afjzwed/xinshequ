package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.TimeCheBuFangActivity;
import com.yxld.yxchuangxin.ui.activity.camera.module.TimeCheBuFangModule;

import dagger.Component;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for TimeCheBuFangActivity
 * @date 2017/09/05 17:39:46
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = TimeCheBuFangModule.class)
public interface TimeCheBuFangComponent {
    TimeCheBuFangActivity inject(TimeCheBuFangActivity Activity);
}