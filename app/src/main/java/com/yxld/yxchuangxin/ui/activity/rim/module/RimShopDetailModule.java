package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimShopDetailActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimShopDetailContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimShopDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.rimshopdetail
 * @Description: The moduele of RimShopDetailActivity, provide field for RimShopDetailActivity
 * @date 2017/12/15 12:04:35
 */
@Module
public class RimShopDetailModule {
    private final RimShopDetailContract.View mView;


    public RimShopDetailModule(RimShopDetailContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RimShopDetailPresenter provideRimShopDetailPresenter(HttpAPIWrapper httpAPIWrapper, RimShopDetailActivity mActivity) {
        return new RimShopDetailPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public RimShopDetailActivity provideRimShopDetailActivity() {
        return (RimShopDetailActivity) mView;
    }
}