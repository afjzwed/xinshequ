package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.YeWeiHuiActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.module.YeWeiHuiModule;

import dagger.Component;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for YeWeiHuiActivity
 * @date 2018/11/07 11:49:57
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = YeWeiHuiModule.class)
public interface YeWeiHuiComponent {
    YeWeiHuiActivity inject(YeWeiHuiActivity Activity);
}