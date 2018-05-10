package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.CameraInformActivity;
import com.yxld.yxchuangxin.ui.activity.camera.module.CameraInformModule;

import dagger.Component;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for CameraInformActivity
 * @date 2017/10/18 09:18:09
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CameraInformModule.class)
public interface CameraInformComponent {
    CameraInformActivity inject(CameraInformActivity Activity);
}