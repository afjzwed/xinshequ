package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.SatisficingActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.SatisficingModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for SatisficingActivity
 * @date 2017/06/20 10:07:42
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = SatisficingModule.class)
public interface SatisficingComponent {
    SatisficingActivity inject(SatisficingActivity Activity);
}