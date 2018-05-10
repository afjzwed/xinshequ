package com.yxld.yxchuangxin.ui.activity.main.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.WebviewActivity;
import com.yxld.yxchuangxin.ui.activity.main.contract.WebviewContract;
import com.yxld.yxchuangxin.ui.activity.main.presenter.WebviewPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: The moduele of WebviewActivity, provide field for WebviewActivity
 * @date 2017/06/23 09:59:44
 */
@Module
public class WebviewModule {
    private final WebviewContract.View mView;


    public WebviewModule(WebviewContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public WebviewPresenter provideWebviewPresenter(HttpAPIWrapper httpAPIWrapper, WebviewActivity mActivity) {
        return new WebviewPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public WebviewActivity provideWebviewActivity() {
        return (WebviewActivity) mView;
    }
}