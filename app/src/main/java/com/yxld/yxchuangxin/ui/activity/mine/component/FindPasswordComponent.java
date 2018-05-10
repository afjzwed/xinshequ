package com.yxld.yxchuangxin.ui.activity.mine.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.mine.FindPasswordActivity;
import com.yxld.yxchuangxin.ui.activity.mine.module.FindPasswordModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: The component for FindPasswordActivity
 * @date 2017/06/23 14:14:05
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FindPasswordModule.class)
public interface FindPasswordComponent {
    FindPasswordActivity inject(FindPasswordActivity Activity);
}