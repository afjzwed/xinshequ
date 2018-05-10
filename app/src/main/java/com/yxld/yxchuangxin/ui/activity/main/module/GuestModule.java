package com.yxld.yxchuangxin.ui.activity.main.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.GuestActivity;
import com.yxld.yxchuangxin.ui.activity.main.contract.GuestContract;
import com.yxld.yxchuangxin.ui.activity.main.presenter.GuestPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: The moduele of GuestActivity, provide field for GuestActivity
 * @date 2017/06/30 10:41:13
 */
@Module
public class GuestModule {
    private final GuestContract.View mView;


    public GuestModule(GuestContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public GuestPresenter provideGuestPresenter(HttpAPIWrapper httpAPIWrapper, GuestActivity mActivity) {
        return new GuestPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public GuestActivity provideGuestActivity() {
        return (GuestActivity) mView;
    }
}