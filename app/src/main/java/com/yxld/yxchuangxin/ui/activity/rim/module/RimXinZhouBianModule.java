package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimXinZhouBianFragment;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimXinZhouBianContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimXinZhouBianPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The moduele of RimXinZhouBianFragment, provide field for RimXinZhouBianFragment
 * @date 2017/06/16
 */
@Module
public class RimXinZhouBianModule {
    private final RimXinZhouBianContract.View mView;


    public RimXinZhouBianModule(RimXinZhouBianContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RimXinZhouBianPresenter provideRimXinZhouBianPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new RimXinZhouBianPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public RimXinZhouBianFragment provideRimXinZhouBianFragment() {
        return (RimXinZhouBianFragment) mView;
    }
}