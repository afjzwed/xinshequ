package com.yxld.yxchuangxin.ui.activity.main.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.FingerLoginActivity;
import com.yxld.yxchuangxin.ui.activity.main.module.FingerLoginModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: The component for FingerLoginActivity
 * @date 2018/04/03 11:46:25
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FingerLoginModule.class)
public interface FingerLoginComponent {
    FingerLoginActivity inject(FingerLoginActivity Activity);
}