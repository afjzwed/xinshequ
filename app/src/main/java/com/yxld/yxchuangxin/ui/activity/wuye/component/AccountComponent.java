package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.AccountActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.AccountModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for AccountActivity
 * @date 2017/06/06
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = AccountModule.class)
public interface AccountComponent {
    AccountActivity inject(AccountActivity Activity);
}