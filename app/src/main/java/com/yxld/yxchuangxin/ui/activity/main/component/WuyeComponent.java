package com.yxld.yxchuangxin.ui.activity.main.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.WuyeFragment;
import com.yxld.yxchuangxin.ui.activity.main.module.WuyeModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: The component for WuyeActivity
 * @date 2017/06/05
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = WuyeModule.class)
public interface WuyeComponent {
    WuyeFragment inject(WuyeFragment Activity);
}