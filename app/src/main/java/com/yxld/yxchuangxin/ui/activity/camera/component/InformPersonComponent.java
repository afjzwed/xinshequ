package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.InformPersonActivity;
import com.yxld.yxchuangxin.ui.activity.camera.module.InformPersonModule;

import dagger.Component;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for InformPersonActivity
 * @date 2017/09/19 11:17:32
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = InformPersonModule.class)
public interface InformPersonComponent {
    InformPersonActivity inject(InformPersonActivity Activity);
}