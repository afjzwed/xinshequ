package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.FourthFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.FourthContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.FourthPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The moduele of FourthFragment, provide field for FourthFragment
 * @date 2018/11/08 11:20:19
 */
@Module
public class FourthModule {
    private final FourthContract.View mView;


    public FourthModule(FourthContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FourthPresenter provideFourthPresenter(HttpAPIWrapper httpAPIWrapper, FourthFragment mFragment) {
        return new FourthPresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public FourthFragment provideFourthFragment() {
        return (FourthFragment) mView;
    }
}