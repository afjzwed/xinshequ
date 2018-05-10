package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.AlarmListFragment;
import com.yxld.yxchuangxin.ui.activity.camera.module.AlarmListModule;

import dagger.Component;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for AlarmListFragment
 * @date 2017/09/04 15:09:38
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = AlarmListModule.class)
public interface AlarmListComponent {
    AlarmListFragment inject(AlarmListFragment Fragment);
}