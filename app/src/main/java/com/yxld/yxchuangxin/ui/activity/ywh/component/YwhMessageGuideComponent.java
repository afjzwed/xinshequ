package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.YwhMessageGuideActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.module.YwhMessageGuideModule;

import dagger.Component;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The component for YwhMessageGuideActivity
 * @date 2018/11/09 16:59:26
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = YwhMessageGuideModule.class)
public interface YwhMessageGuideComponent {
    YwhMessageGuideActivity inject(YwhMessageGuideActivity Activity);
}