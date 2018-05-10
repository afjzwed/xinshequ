package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.PlayBackActivity;
import com.yxld.yxchuangxin.ui.activity.camera.module.PlayBackModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for PlayBackActivity
 * @date 2017/06/21 10:22:52
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = PlayBackModule.class)
public interface PlayBackComponent {
    PlayBackActivity inject(PlayBackActivity Activity);
}