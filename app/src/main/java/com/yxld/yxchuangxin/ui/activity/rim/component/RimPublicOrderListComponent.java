package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimPublicOrderListFragment;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimPublicOrderListModule;

import dagger.Component;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The component for RimPublicOrderListFragment
 * @date 2017/06/17
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RimPublicOrderListModule.class)
public interface RimPublicOrderListComponent {
    RimPublicOrderListFragment inject(RimPublicOrderListFragment Fragment);
}