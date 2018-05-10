package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.FangwuActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.FangwuModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for FangwuActivity
 * @date 2017/06/06
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FangwuModule.class)
public interface FangwuComponent {
    FangwuActivity inject(FangwuActivity Activity);
}