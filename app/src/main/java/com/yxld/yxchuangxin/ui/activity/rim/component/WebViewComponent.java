package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.WebViewActivity;
import com.yxld.yxchuangxin.ui.activity.rim.module.WebViewModule;

import dagger.Component;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The component for WebViewActivity
 * @date 2017/06/17
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = WebViewModule.class)
public interface WebViewComponent {
    WebViewActivity inject(WebViewActivity Activity);
}