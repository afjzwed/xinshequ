package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.GoodListFragment;
import com.yxld.yxchuangxin.ui.activity.rim.module.GoodListModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The component for GoodListFragment
 * @date 2017/06/17
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = GoodListModule.class)
public interface GoodListComponent {
    GoodListFragment inject(GoodListFragment Fragment);
}