package com.yxld.yxchuangxin.ui.activity.common.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.common.RealPlayActivity;
import com.yxld.yxchuangxin.ui.activity.common.module.RealPlayModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.common
 * @Description: The component for RealPlayActivity
 * @date 2017/06/08
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RealPlayModule.class)
public interface RealPlayComponent {
    RealPlayActivity inject(RealPlayActivity Activity);
}