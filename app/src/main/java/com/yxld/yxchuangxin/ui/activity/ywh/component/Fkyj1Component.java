package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.Fkyj1Fragment;
import com.yxld.yxchuangxin.ui.activity.ywh.module.Fkyj1Module;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The component for Fkyj1Fragment
 * @date 2018/11/09 13:26:28
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = Fkyj1Module.class)
public interface Fkyj1Component {
    Fkyj1Fragment inject(Fkyj1Fragment Fragment);
}