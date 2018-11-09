package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.YwhMessageFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.YwhMessageContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.YwhMessagePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The moduele of YwhMessageFragment, provide field for YwhMessageFragment
 * @date 2018/11/09 17:04:05
 */
@Module
public class YwhMessageModule {
    private final YwhMessageContract.View mView;


    public YwhMessageModule(YwhMessageContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public YwhMessagePresenter provideYwhMessagePresenter(HttpAPIWrapper httpAPIWrapper, YwhMessageFragment mFragment) {
        return new YwhMessagePresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public YwhMessageFragment provideYwhMessageFragment() {
        return (YwhMessageFragment) mView;
    }
}