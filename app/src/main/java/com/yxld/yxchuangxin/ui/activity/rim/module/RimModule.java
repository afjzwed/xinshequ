package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimFragment;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The moduele of RimActivity, provide field for RimActivity
 * @date 2017/06/12
 */
@Module
public class RimModule {
    private final RimContract.View mView;


    public RimModule(RimContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RimPresenter provideRimPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new RimPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public RimFragment provideRimActivity() {
        return (RimFragment) mView;
    }
}