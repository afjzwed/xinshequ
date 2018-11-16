package com.yxld.yxchuangxin.ui.activity.main.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.MainNewFragment;
import com.yxld.yxchuangxin.ui.activity.main.module.MainNewModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: The component for MainNewFragment
 * @date 2018/11/16 10:36:55
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MainNewModule.class)
public interface MainNewComponent {
    MainNewFragment inject(MainNewFragment Fragment);
}