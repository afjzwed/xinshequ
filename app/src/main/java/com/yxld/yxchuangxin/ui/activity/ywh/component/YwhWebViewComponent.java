package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.YwhWebViewActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.module.YwhWebViewModule;

import dagger.Component;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The component for YwhWebViewActivity
 * @date 2018/11/14 14:14:11
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = YwhWebViewModule.class)
public interface YwhWebViewComponent {
    YwhWebViewActivity inject(YwhWebViewActivity Activity);
}