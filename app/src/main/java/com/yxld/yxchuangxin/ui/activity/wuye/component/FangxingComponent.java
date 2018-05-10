package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.FangxingActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.FangxingModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for FangxingActivity
 * @date 2017/06/13
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FangxingModule.class)
public interface FangxingComponent {
    FangxingActivity inject(FangxingActivity Activity);
}