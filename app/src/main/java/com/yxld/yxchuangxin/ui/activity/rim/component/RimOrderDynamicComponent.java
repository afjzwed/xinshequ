package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimOrderDynamicActivity;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimOrderDynamicModule;

import dagger.Component;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The component for RimOrderDynamicActivity
 * @date 2017/06/17
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RimOrderDynamicModule.class)
public interface RimOrderDynamicComponent {
    RimOrderDynamicActivity inject(RimOrderDynamicActivity Activity);
}