package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.YeZhuFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.module.YeZhuModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for YeZhuFragment
 * @date 2017/06/06
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = YeZhuModule.class)
public interface YeZhuComponent {
    YeZhuFragment inject(YeZhuFragment Fragment);
}