package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.WebSatisficingActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.WebSatisficingModule;

import dagger.Component;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.websatisficing
 * @Description: The component for WebSatisficingActivity
 * @date 2017/12/22 18:18:04
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = WebSatisficingModule.class)
public interface WebSatisficingComponent {
    WebSatisficingActivity inject(WebSatisficingActivity Activity);
}