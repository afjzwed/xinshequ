package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimComplainAddActivity;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimComplainAddModule;

import dagger.Component;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The component for RimComplainAddActivity
 * @date 2017/06/17
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RimComplainAddModule.class)
public interface RimComplainAddComponent {
    RimComplainAddActivity inject(RimComplainAddActivity Activity);
}