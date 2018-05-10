package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimFragment;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The component for RimActivity
 * @date 2017/06/12
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RimModule.class)
public interface RimComponent {
    RimFragment inject(RimFragment Activity);
}