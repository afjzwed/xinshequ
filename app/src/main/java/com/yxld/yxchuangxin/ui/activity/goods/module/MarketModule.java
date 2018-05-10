package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.MarketFragment;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MarketContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.MarketPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of MarketActivity, provide field for MarketActivity
 * @date 2017/06/12
 */
@Module
public class MarketModule {
    private final MarketContract.View mView;


    public MarketModule(MarketContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MarketPresenter provideMarketPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new MarketPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public MarketFragment provideMarketActivity() {
        return (MarketFragment) mView;
    }
}