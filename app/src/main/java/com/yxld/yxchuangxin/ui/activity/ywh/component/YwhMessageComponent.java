package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.YwhMessageFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.module.YwhMessageModule;

import dagger.Component;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The component for YwhMessageFragment
 * @date 2018/11/09 17:04:05
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = YwhMessageModule.class)
public interface YwhMessageComponent {
    YwhMessageFragment inject(YwhMessageFragment Fragment);
}