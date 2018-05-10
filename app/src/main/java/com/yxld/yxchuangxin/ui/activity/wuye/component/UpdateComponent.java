package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.UpdateActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.UpdateModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for UpdateActivity
 * @date 2017/06/23 09:25:59
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = UpdateModule.class)
public interface UpdateComponent {
    UpdateActivity inject(UpdateActivity Activity);
}