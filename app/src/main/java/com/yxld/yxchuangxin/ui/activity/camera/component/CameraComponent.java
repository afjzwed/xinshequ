package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.CameraActivity;
import com.yxld.yxchuangxin.ui.activity.camera.module.CameraModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for CameraActivity
 * @date 2017/06/21 10:19:03
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CameraModule.class)
public interface CameraComponent {
    CameraActivity inject(CameraActivity Activity);
}