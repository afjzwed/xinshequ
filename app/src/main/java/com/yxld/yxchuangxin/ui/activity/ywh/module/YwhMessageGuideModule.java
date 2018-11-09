package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.YwhMessageGuideActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.YwhMessageGuideContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.YwhMessageGuidePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The moduele of YwhMessageGuideActivity, provide field for YwhMessageGuideActivity
 * @date 2018/11/09 16:59:26
 */
@Module
public class YwhMessageGuideModule {
    private final YwhMessageGuideContract.View mView;


    public YwhMessageGuideModule(YwhMessageGuideContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public YwhMessageGuidePresenter provideYwhMessageGuidePresenter(HttpAPIWrapper httpAPIWrapper, YwhMessageGuideActivity mActivity) {
        return new YwhMessageGuidePresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public YwhMessageGuideActivity provideYwhMessageGuideActivity() {
        return (YwhMessageGuideActivity) mView;
    }
}