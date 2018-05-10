package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.AddfangxingActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.AddfangxingModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for AddfangxingActivity
 * @date 2017/06/13
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = AddfangxingModule.class)
public interface AddfangxingComponent {
    AddfangxingActivity inject(AddfangxingActivity Activity);
}