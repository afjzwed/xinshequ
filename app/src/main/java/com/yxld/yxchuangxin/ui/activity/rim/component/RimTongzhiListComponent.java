package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimTongzhiListActivity;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimTongzhiListModule;

import dagger.Component;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The component for RimTongzhiListActivity
 * @date 2017/06/17
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RimTongzhiListModule.class)
public interface RimTongzhiListComponent {
    RimTongzhiListActivity inject(RimTongzhiListActivity Activity);
}