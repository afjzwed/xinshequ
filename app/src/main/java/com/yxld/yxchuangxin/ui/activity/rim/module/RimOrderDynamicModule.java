package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimOrderDynamicActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimOrderDynamicContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimOrderDynamicPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The moduele of RimOrderDynamicActivity, provide field for RimOrderDynamicActivity
 * @date 2017/06/17
 */
@Module
public class RimOrderDynamicModule {
    private final RimOrderDynamicContract.View mView;


    public RimOrderDynamicModule(RimOrderDynamicContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RimOrderDynamicPresenter provideRimOrderDynamicPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new RimOrderDynamicPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public RimOrderDynamicActivity provideRimOrderDynamicActivity() {
        return (RimOrderDynamicActivity) mView;
    }
}