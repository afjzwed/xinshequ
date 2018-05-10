package com.yxld.yxchuangxin.ui.activity.common.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.common.AisleActivity;
import com.yxld.yxchuangxin.ui.activity.common.module.AisleModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.commom
 * @Description: The component for AisleActivity
 * @date 2017/06/08
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = AisleModule.class)
public interface AisleComponent {
    AisleActivity inject(AisleActivity Activity);
}