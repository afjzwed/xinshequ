package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.YwhWebViewActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.YwhWebViewContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.YwhWebViewPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The moduele of YwhWebViewActivity, provide field for YwhWebViewActivity
 * @date 2018/11/14 14:14:11
 */
@Module
public class YwhWebViewModule {
    private final YwhWebViewContract.View mView;


    public YwhWebViewModule(YwhWebViewContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public YwhWebViewPresenter provideYwhWebViewPresenter(HttpAPIWrapper httpAPIWrapper, YwhWebViewActivity mActivity) {
        return new YwhWebViewPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public YwhWebViewActivity provideYwhWebViewActivity() {
        return (YwhWebViewActivity) mView;
    }
}