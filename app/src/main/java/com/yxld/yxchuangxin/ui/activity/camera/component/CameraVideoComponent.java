package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.CameraVideoActivity;
import com.yxld.yxchuangxin.ui.activity.camera.module.CameraVideoModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for CameraVideoActivity
 * @date 2017/06/21 10:22:39
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CameraVideoModule.class)
public interface CameraVideoComponent {
    CameraVideoActivity inject(CameraVideoActivity Activity);
}