package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.GoodListFragment;
import com.yxld.yxchuangxin.ui.activity.rim.contract.GoodListContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.GoodListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The moduele of GoodListFragment, provide field for GoodListFragment
 * @date 2017/06/17
 */
@Module
public class GoodListModule {
    private final GoodListContract.View mView;


    public GoodListModule(GoodListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public GoodListPresenter provideGoodListPresenter(HttpAPIWrapper httpAPIWrapper, GoodListFragment fragment) {
        return new GoodListPresenter(httpAPIWrapper, mView, fragment);
    }

    @Provides
    @ActivityScope
    public GoodListFragment provideGoodListFragment() {
        return (GoodListFragment) mView;
    }
}