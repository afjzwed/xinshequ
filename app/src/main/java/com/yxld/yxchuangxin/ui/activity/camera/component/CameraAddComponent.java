package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.CameraAddActivity;
import com.yxld.yxchuangxin.ui.activity.camera.module.CameraAddModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for CameraAddActivity
 * @date 2017/06/21 10:21:55
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CameraAddModule.class)
public interface CameraAddComponent {
    CameraAddActivity inject(CameraAddActivity Activity);
}