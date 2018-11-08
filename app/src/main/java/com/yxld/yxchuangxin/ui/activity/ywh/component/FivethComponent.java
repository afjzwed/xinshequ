package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.FivethFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.module.FivethModule;

import dagger.Component;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The component for FivethFragment
 * @date 2018/11/08 14:11:35
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FivethModule.class)
public interface FivethComponent {
    FivethFragment inject(FivethFragment Fragment);
}