package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.FixActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.FixModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for FixActivity
 * @date 2017/06/15
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FixModule.class)
public interface FixComponent {
    FixActivity inject(FixActivity Activity);
}