package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.Fkyj2Fragment;
import com.yxld.yxchuangxin.ui.activity.ywh.module.Fkyj2Module;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The component for Fkyj2Fragment
 * @date 2018/11/09 13:42:26
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = Fkyj2Module.class)
public interface Fkyj2Component {
    Fkyj2Fragment inject(Fkyj2Fragment Fragment);
}