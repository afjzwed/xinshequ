package com.yxld.yxchuangxin.ui.activity.mine.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.mine.AddAccountActivity;
import com.yxld.yxchuangxin.ui.activity.mine.module.AddAccountModule;

import dagger.Component;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: The component for AddAccountActivity
 * @date 2017/10/11 09:49:50
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = AddAccountModule.class)
public interface AddAccountComponent {
    AddAccountActivity inject(AddAccountActivity Activity);
}