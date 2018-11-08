package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.TwoFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.TwoContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.TwoPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The moduele of TwoFragment, provide field for TwoFragment
 * @date 2018/11/08 09:44:57
 */
@Module
public class TwoModule {
    private final TwoContract.View mView;


    public TwoModule(TwoContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public TwoPresenter provideTwoPresenter(HttpAPIWrapper httpAPIWrapper, TwoFragment mFragment) {
        return new TwoPresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public TwoFragment provideTwoFragment() {
        return (TwoFragment) mView;
    }
}