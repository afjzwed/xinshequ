package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.FkyjActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.module.FkyjModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The component for FkyjActivity
 * @date 2018/11/09 10:03:28
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FkyjModule.class)
public interface FkyjComponent {
    FkyjActivity inject(FkyjActivity Activity);
}