package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimOrderListActivityActivity;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimOrderListActivityModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The component for RimOrderListActivityActivity
 * @date 2017/12/14 08:25:35
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RimOrderListActivityModule.class)
public interface RimOrderListActivityComponent {
    RimOrderListActivityActivity inject(RimOrderListActivityActivity Activity);
}