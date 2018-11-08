package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.OneFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.OneContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.OnePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The moduele of OneFragment, provide field for OneFragment
 * @date 2018/11/08 08:54:54
 */
@Module
public class OneModule {
    private final OneContract.View mView;


    public OneModule(OneContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public OnePresenter provideOnePresenter(HttpAPIWrapper httpAPIWrapper, OneFragment mFragment) {
        return new OnePresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public OneFragment provideOneFragment() {
        return (OneFragment) mView;
    }
}