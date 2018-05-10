package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.LearnActivity;
import com.yxld.yxchuangxin.ui.activity.camera.module.LearnModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for LearnActivity
 * @date 2017/06/21 10:22:13
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = LearnModule.class)
public interface LearnComponent {
    LearnActivity inject(LearnActivity Activity);
}