package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.CameraConfigActivity;
import com.yxld.yxchuangxin.ui.activity.camera.module.CameraConfigModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for CameraConfigActivity
 * @date 2017/06/21 10:21:40
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CameraConfigModule.class)
public interface CameraConfigComponent {
    CameraConfigActivity inject(CameraConfigActivity Activity);
}