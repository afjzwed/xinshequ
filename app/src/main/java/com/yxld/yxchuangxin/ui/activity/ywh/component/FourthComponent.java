package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.FourthFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.module.FourthModule;

import dagger.Component;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The component for FourthFragment
 * @date 2018/11/08 11:20:19
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FourthModule.class)
public interface FourthComponent {
    FourthFragment inject(FourthFragment Fragment);
}