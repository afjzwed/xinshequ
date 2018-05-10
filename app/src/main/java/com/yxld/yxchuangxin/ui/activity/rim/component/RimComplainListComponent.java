package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimComplainListActivity;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimComplainListModule;

import dagger.Component;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The component for RimComplainListActivity
 * @date 2017/06/16
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RimComplainListModule.class)
public interface RimComplainListComponent {
    RimComplainListActivity inject(RimComplainListActivity Activity);
}