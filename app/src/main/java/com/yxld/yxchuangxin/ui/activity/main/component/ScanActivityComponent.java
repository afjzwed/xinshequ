package com.yxld.yxchuangxin.ui.activity.main.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.ScanActivityActivity;
import com.yxld.yxchuangxin.ui.activity.main.module.ScanActivityModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: The component for ScanActivityActivity
 * @date 2018/03/02 09:11:52
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ScanActivityModule.class)
public interface ScanActivityComponent {
    ScanActivityActivity inject(ScanActivityActivity Activity);
}