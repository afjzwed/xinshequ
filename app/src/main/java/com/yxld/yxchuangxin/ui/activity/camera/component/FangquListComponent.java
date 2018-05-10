package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.FangquListActivity;
import com.yxld.yxchuangxin.ui.activity.camera.module.FangquListModule;

import dagger.Component;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for FangquListActivity
 * @date 2017/09/07 01:27:17
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FangquListModule.class)
public interface FangquListComponent {
    FangquListActivity inject(FangquListActivity Activity);
}