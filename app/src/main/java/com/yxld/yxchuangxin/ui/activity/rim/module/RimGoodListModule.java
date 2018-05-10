package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimGoodListFragment;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimGoodListContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimGoodListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The moduele of RimGoodListFragment, provide field for RimGoodListFragment
 * @date 2017/12/13 10:44:31
 */
@Module
public class RimGoodListModule {
    private final RimGoodListContract.View mView;


    public RimGoodListModule(RimGoodListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RimGoodListPresenter provideRimGoodListPresenter(HttpAPIWrapper httpAPIWrapper, RimGoodListFragment mFragment) {
        return new RimGoodListPresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public RimGoodListFragment provideRimGoodListFragment() {
        return (RimGoodListFragment) mView;
    }
}