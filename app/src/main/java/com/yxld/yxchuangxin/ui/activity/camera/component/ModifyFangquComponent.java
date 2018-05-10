package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.ModifyFangquActivity;
import com.yxld.yxchuangxin.ui.activity.camera.module.ModifyFangquModule;

import dagger.Component;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for ModifyFangquActivity
 * @date 2017/09/19 17:43:45
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ModifyFangquModule.class)
public interface ModifyFangquComponent {
    ModifyFangquActivity inject(ModifyFangquActivity Activity);
}