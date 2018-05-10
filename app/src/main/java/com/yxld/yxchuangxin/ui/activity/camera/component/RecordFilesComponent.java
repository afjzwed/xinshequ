package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.RecordFilesActivity;
import com.yxld.yxchuangxin.ui.activity.camera.module.RecordFilesModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for RecordFilesActivity
 * @date 2017/06/21 10:22:29
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RecordFilesModule.class)
public interface RecordFilesComponent {
    RecordFilesActivity inject(RecordFilesActivity Activity);
}