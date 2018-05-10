package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimComplainDetailActivity;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimComplainDetailModule;

import dagger.Component;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.rimcomplaindetail
 * @Description: The component for RimComplainDetailActivity
 * @date 2017/12/21 15:47:26
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RimComplainDetailModule.class)
public interface RimComplainDetailComponent {
    RimComplainDetailActivity inject(RimComplainDetailActivity Activity);
}