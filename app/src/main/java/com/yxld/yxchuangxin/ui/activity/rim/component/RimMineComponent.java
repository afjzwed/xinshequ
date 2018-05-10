package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimMineFragment;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimMineModule;

import dagger.Component;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The component for RimMineFragment
 * @date 2017/06/16
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RimMineModule.class)
public interface RimMineComponent {
    RimMineFragment inject(RimMineFragment Fragment);
}