package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.ThirdFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.ThirdContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.ThirdPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The moduele of ThirdFragment, provide field for ThirdFragment
 * @date 2018/11/08 09:53:49
 */
@Module
public class ThirdModule {
    private final ThirdContract.View mView;


    public ThirdModule(ThirdContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ThirdPresenter provideThirdPresenter(HttpAPIWrapper httpAPIWrapper, ThirdFragment mFragment) {
        return new ThirdPresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public ThirdFragment provideThirdFragment() {
        return (ThirdFragment) mView;
    }
}