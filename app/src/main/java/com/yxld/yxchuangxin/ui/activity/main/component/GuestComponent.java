package com.yxld.yxchuangxin.ui.activity.main.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.GuestActivity;
import com.yxld.yxchuangxin.ui.activity.main.module.GuestModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: The component for GuestActivity
 * @date 2017/06/30 10:41:13
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = GuestModule.class)
public interface GuestComponent {
    GuestActivity inject(GuestActivity Activity);
}