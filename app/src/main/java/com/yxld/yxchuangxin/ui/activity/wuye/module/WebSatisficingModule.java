package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.WebSatisficingActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.WebSatisficingContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.WebSatisficingPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.websatisficing
 * @Description: The moduele of WebSatisficingActivity, provide field for WebSatisficingActivity
 * @date 2017/12/22 18:18:04
 */
@Module
public class WebSatisficingModule {
    private final WebSatisficingContract.View mView;


    public WebSatisficingModule(WebSatisficingContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public WebSatisficingPresenter provideWebSatisficingPresenter(HttpAPIWrapper httpAPIWrapper, WebSatisficingActivity mActivity) {
        return new WebSatisficingPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public WebSatisficingActivity provideWebSatisficingActivity() {
        return (WebSatisficingActivity) mView;
    }
}