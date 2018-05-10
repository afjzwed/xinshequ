package com.yxld.yxchuangxin.ui.activity.main.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.WebviewActivity;
import com.yxld.yxchuangxin.ui.activity.main.module.WebviewModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: The component for WebviewActivity
 * @date 2017/06/23 09:59:44
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = WebviewModule.class)
public interface WebviewComponent {
    WebviewActivity inject(WebviewActivity Activity);
}