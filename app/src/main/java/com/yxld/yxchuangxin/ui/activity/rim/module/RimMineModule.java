package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimMineFragment;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimMineContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimMinePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The moduele of RimMineFragment, provide field for RimMineFragment
 * @date 2017/06/16
 */
@Module
public class RimMineModule {
    private final RimMineContract.View mView;


    public RimMineModule(RimMineContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RimMinePresenter provideRimMinePresenter(HttpAPIWrapper httpAPIWrapper) {
        return new RimMinePresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public RimMineFragment provideRimMineFragment() {
        return (RimMineFragment) mView;
    }
}