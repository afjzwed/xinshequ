package com.yxld.yxchuangxin.ui.activity.main.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.SplashActivity;
import com.yxld.yxchuangxin.ui.activity.main.contract.SplashContract;
import com.yxld.yxchuangxin.ui.activity.main.presenter.SplashPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: The moduele of SplashActivity, provide field for SplashActivity
 * @date 2017/06/05
 */
@Module
public class SplashModule {
    private final SplashContract.View mView;


    public SplashModule(SplashContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public SplashPresenter provideSplashPresenter(HttpAPIWrapper httpAPIWrapper, SplashActivity splashActivity) {
        return new SplashPresenter(httpAPIWrapper, mView, splashActivity);
    }

    @Provides
    @ActivityScope
    public SplashActivity provideSplashActivity() {
        return (SplashActivity) mView;
    }
}