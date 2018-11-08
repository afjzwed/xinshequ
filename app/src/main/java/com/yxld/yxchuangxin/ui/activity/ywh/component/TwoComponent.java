package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.TwoFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.module.TwoModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The component for TwoFragment
 * @date 2018/11/08 09:44:57
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = TwoModule.class)
public interface TwoComponent {
    TwoFragment inject(TwoFragment Fragment);
}