package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.PaySelectActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.PaySelectModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for PaySelectActivity
 * @date 2017/07/07 11:10:46
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = PaySelectModule.class)
public interface PaySelectComponent {
    PaySelectActivity inject(PaySelectActivity Activity);
}