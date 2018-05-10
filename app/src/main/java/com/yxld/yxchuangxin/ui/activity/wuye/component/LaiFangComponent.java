package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.LaiFangFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.module.LaiFangModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for LaiFangFragment
 * @date 2017/06/06
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = LaiFangModule.class)
public interface LaiFangComponent {
    LaiFangFragment inject(LaiFangFragment Fragment);
}