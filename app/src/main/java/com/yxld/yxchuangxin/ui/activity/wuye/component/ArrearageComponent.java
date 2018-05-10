package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.ArrearageFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.module.ArrearageModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for ArrearageFragment
 * @date 2017/07/01 13:46:50
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ArrearageModule.class)
public interface ArrearageComponent {
    ArrearageFragment inject(ArrearageFragment Fragment);
}