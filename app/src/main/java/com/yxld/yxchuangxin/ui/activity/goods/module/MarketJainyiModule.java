package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.MarketJainyiActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MarketJainyiContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.MarketJainyiPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of MarketJainyiActivity, provide field for MarketJainyiActivity
 * @date 2017/06/22 16:19:40
 */
@Module
public class MarketJainyiModule {
    private final MarketJainyiContract.View mView;


    public MarketJainyiModule(MarketJainyiContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MarketJainyiPresenter provideMarketJainyiPresenter(HttpAPIWrapper httpAPIWrapper, MarketJainyiActivity mActivity) {
        return new MarketJainyiPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public MarketJainyiActivity provideMarketJainyiActivity() {
        return (MarketJainyiActivity) mView;
    }
}