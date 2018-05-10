package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.SearchActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.SearchContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.SearchPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of SearchActivity, provide field for SearchActivity
 * @date 2017/06/24 10:26:33
 */
@Module
public class SearchModule {
    private final SearchContract.View mView;


    public SearchModule(SearchContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public SearchPresenter provideSearchPresenter(HttpAPIWrapper httpAPIWrapper, SearchActivity mActivity) {
        return new SearchPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public SearchActivity provideSearchActivity() {
        return (SearchActivity) mView;
    }
}