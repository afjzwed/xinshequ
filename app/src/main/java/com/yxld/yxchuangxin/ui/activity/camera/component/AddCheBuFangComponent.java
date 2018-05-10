package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.AddCheBuFangActivity;
import com.yxld.yxchuangxin.ui.activity.camera.module.AddCheBuFangModule;

import dagger.Component;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for AddCheBuFangActivity
 * @date 2017/09/05 18:10:45
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = AddCheBuFangModule.class)
public interface AddCheBuFangComponent {
    AddCheBuFangActivity inject(AddCheBuFangActivity Activity);
}