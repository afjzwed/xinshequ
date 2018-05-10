package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.FixInFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.module.FixInModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for FixInFragment
 * @date 2017/06/15
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FixInModule.class)
public interface FixInComponent {
    FixInFragment inject(FixInFragment Fragment);
}