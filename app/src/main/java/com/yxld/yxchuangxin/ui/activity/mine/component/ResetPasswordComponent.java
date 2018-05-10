package com.yxld.yxchuangxin.ui.activity.mine.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.mine.ResetPasswordActivity;
import com.yxld.yxchuangxin.ui.activity.mine.module.ResetPasswordModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: The component for ResetPasswordActivity
 * @date 2017/06/23 11:03:59
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ResetPasswordModule.class)
public interface ResetPasswordComponent {
    ResetPasswordActivity inject(ResetPasswordActivity Activity);
}