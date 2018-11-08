package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.SixthFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.SixthContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.SixthPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The moduele of SixthFragment, provide field for SixthFragment
 * @date 2018/11/08 15:56:44
 */
@Module
public class SixthModule {
    private final SixthContract.View mView;


    public SixthModule(SixthContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public SixthPresenter provideSixthPresenter(HttpAPIWrapper httpAPIWrapper, SixthFragment mFragment) {
        return new SixthPresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public SixthFragment provideSixthFragment() {
        return (SixthFragment) mView;
    }
}