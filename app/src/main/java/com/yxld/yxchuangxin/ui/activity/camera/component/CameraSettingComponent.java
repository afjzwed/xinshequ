package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.CameraSettingActivity;
import com.yxld.yxchuangxin.ui.activity.camera.module.CameraSettingModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for CameraSettingActivity
 * @date 2017/06/21 10:21:20
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CameraSettingModule.class)
public interface CameraSettingComponent {
    CameraSettingActivity inject(CameraSettingActivity Activity);
}