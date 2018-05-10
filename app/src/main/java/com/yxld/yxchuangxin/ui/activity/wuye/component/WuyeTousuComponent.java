package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.WuyeTousuFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.module.WuyeTousuModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for WuyeTousuFragment
 * @date 2017/06/20 11:11:21
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = WuyeTousuModule.class)
public interface WuyeTousuComponent {
    WuyeTousuFragment inject(WuyeTousuFragment Fragment);
}