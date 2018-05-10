package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.WuyeJianyiFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.module.WuyeJianyiModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for WuyeJianyiFragment
 * @date 2017/06/20 11:11:36
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = WuyeJianyiModule.class)
public interface WuyeJianyiComponent {
    WuyeJianyiFragment inject(WuyeJianyiFragment Fragment);
}