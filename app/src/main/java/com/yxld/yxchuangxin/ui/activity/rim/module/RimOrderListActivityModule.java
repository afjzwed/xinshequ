package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimOrderListActivityActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimOrderListActivityContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimOrderListActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The moduele of RimOrderListActivityActivity, provide field for RimOrderListActivityActivity
 * @date 2017/12/14 08:25:35
 */
@Module
public class RimOrderListActivityModule {
    private final RimOrderListActivityContract.View mView;


    public RimOrderListActivityModule(RimOrderListActivityContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RimOrderListActivityPresenter provideRimOrderListActivityPresenter(HttpAPIWrapper httpAPIWrapper, RimOrderListActivityActivity mActivity) {
        return new RimOrderListActivityPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public RimOrderListActivityActivity provideRimOrderListActivityActivity() {
        return (RimOrderListActivityActivity) mView;
    }
}