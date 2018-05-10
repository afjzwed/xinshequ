package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimComplainAddActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimComplainAddContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimComplainAddPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The moduele of RimComplainAddActivity, provide field for RimComplainAddActivity
 * @date 2017/06/17
 */
@Module
public class RimComplainAddModule {
    private final RimComplainAddContract.View mView;


    public RimComplainAddModule(RimComplainAddContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RimComplainAddPresenter provideRimComplainAddPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new RimComplainAddPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public RimComplainAddActivity provideRimComplainAddActivity() {
        return (RimComplainAddActivity) mView;
    }
}