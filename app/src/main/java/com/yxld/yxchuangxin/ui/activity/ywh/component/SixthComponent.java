package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.SixthFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.module.SixthModule;

import dagger.Component;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The component for SixthFragment
 * @date 2018/11/08 15:56:44
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = SixthModule.class)
public interface SixthComponent {
    SixthFragment inject(SixthFragment Fragment);
}