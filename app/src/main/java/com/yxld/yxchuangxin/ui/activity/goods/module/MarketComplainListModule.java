package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.MarketComplainListActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MarketComplainListContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.MarketComplainListPresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.MarketSuggestListAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of MarketComplainListActivity, provide field for MarketComplainListActivity
 * @date 2017/11/02 14:28:33
 */
@Module
public class MarketComplainListModule {
    private final MarketComplainListContract.View mView;


    public MarketComplainListModule(MarketComplainListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MarketComplainListPresenter provideMarketComplainListPresenter(HttpAPIWrapper httpAPIWrapper, MarketComplainListActivity mActivity) {
        return new MarketComplainListPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public MarketComplainListActivity provideMarketComplainListActivity() {
        return (MarketComplainListActivity) mView;
    }

    @Provides
    @ActivityScope
    public MarketSuggestListAdapter provideMarketSuggestListAdapter() {
        return new MarketSuggestListAdapter(new ArrayList<>());
    }
}