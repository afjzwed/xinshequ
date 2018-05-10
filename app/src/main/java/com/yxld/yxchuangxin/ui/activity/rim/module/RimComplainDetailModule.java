package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimComplainDetailActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimComplainDetailContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimComplainDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.rimcomplaindetail
 * @Description: The moduele of RimComplainDetailActivity, provide field for RimComplainDetailActivity
 * @date 2017/12/21 15:47:26
 */
@Module
public class RimComplainDetailModule {
    private final RimComplainDetailContract.View mView;


    public RimComplainDetailModule(RimComplainDetailContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RimComplainDetailPresenter provideRimComplainDetailPresenter(HttpAPIWrapper httpAPIWrapper, RimComplainDetailActivity mActivity) {
        return new RimComplainDetailPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public RimComplainDetailActivity provideRimComplainDetailActivity() {
        return (RimComplainDetailActivity) mView;
    }
}