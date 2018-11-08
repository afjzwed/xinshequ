package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.FivethFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.FivethContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.FivethPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The moduele of FivethFragment, provide field for FivethFragment
 * @date 2018/11/08 14:11:35
 */
@Module
public class FivethModule {
    private final FivethContract.View mView;


    public FivethModule(FivethContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FivethPresenter provideFivethPresenter(HttpAPIWrapper httpAPIWrapper, FivethFragment mFragment) {
        return new FivethPresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public FivethFragment provideFivethFragment() {
        return (FivethFragment) mView;
    }
}