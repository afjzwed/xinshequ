package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.AboutOurActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.AboutOurModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for AboutOurActivity
 * @date 2017/06/23 09:20:36
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = AboutOurModule.class)
public interface AboutOurComponent {
    AboutOurActivity inject(AboutOurActivity Activity);
}