package com.yxld.yxchuangxin.ui.activity.common.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.common.RealPlayActivity;
import com.yxld.yxchuangxin.ui.activity.common.contract.RealPlayContract;
import com.yxld.yxchuangxin.ui.activity.common.presenter.RealPlayPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.common
 * @Description: The moduele of RealPlayActivity, provide field for RealPlayActivity
 * @date 2017/06/08
 */
@Module
public class RealPlayModule {
    private final RealPlayContract.View mView;


    public RealPlayModule(RealPlayContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RealPlayPresenter provideRealPlayPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new RealPlayPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public RealPlayActivity provideRealPlayActivity() {
        return (RealPlayActivity) mView;
    }
}