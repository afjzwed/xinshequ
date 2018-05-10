package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimComplainListActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimComplainListContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimComplainListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The moduele of RimComplainListActivity, provide field for RimComplainListActivity
 * @date 2017/06/16
 */
@Module
public class RimComplainListModule {
    private final RimComplainListContract.View mView;


    public RimComplainListModule(RimComplainListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RimComplainListPresenter provideRimComplainListPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new RimComplainListPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public RimComplainListActivity provideRimComplainListActivity() {
        return (RimComplainListActivity) mView;
    }
}