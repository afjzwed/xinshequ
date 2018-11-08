package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.OneFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.module.OneModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The component for OneFragment
 * @date 2018/11/08 08:54:54
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = OneModule.class)
public interface OneComponent {
    OneFragment inject(OneFragment Fragment);
}