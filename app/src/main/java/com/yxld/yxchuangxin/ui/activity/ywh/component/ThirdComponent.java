package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.ThirdFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.module.ThirdModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The component for ThirdFragment
 * @date 2018/11/08 09:53:49
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ThirdModule.class)
public interface ThirdComponent {
    ThirdFragment inject(ThirdFragment Fragment);
}