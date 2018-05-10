package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.MarketComplainActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MarketComplainContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.MarketComplainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of MarketComplainActivity, provide field for MarketComplainActivity
 * @date 2017/06/22 15:30:24
 */
@Module
public class MarketComplainModule {
    private final MarketComplainContract.View mView;


    public MarketComplainModule(MarketComplainContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MarketComplainPresenter provideMarketComplainPresenter(HttpAPIWrapper httpAPIWrapper, MarketComplainActivity mActivity) {
        return new MarketComplainPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public MarketComplainActivity provideMarketComplainActivity() {
        return (MarketComplainActivity) mView;
    }
}