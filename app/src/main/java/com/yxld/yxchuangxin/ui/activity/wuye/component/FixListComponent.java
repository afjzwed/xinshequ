package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.FixListActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.FixListModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for FixListActivity
 * @date 2017/06/15
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FixListModule.class)
public interface FixListComponent {
    FixListActivity inject(FixListActivity Activity);
}