package com.yxld.yxchuangxin.ui.activity.xiongmai.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.xiongmai.XCameraActivity;
import com.yxld.yxchuangxin.ui.activity.xiongmai.module.XCameraModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.xiongmai
 * @Description: The component for XCameraActivity
 * @date 2017/07/17 16:30:10
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = XCameraModule.class)
public interface XCameraComponent {
    XCameraActivity inject(XCameraActivity Activity);
}