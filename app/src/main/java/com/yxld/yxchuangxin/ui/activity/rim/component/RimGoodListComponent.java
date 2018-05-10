package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimGoodListFragment;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimGoodListModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The component for RimGoodListFragment
 * @date 2017/12/13 10:44:31
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RimGoodListModule.class)
public interface RimGoodListComponent {
    RimGoodListFragment inject(RimGoodListFragment Fragment);
}