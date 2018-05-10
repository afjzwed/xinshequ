package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.WebViewActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.WebViewContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.WebViewPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The moduele of WebViewActivity, provide field for WebViewActivity
 * @date 2017/06/17
 */
@Module
public class WebViewModule {
    private final WebViewContract.View mView;


    public WebViewModule(WebViewContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public WebViewPresenter provideWebViewPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new WebViewPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public WebViewActivity provideWebViewActivity() {
        return (WebViewActivity) mView;
    }
}