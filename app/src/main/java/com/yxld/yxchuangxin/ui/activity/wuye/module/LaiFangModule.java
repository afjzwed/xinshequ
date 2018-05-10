package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.LaiFangFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.LaiFangContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.LaiFangPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of LaiFangFragment, provide field for LaiFangFragment
 * @date 2017/06/06
 */
@Module
public class LaiFangModule {
    private final LaiFangContract.View mView;


    public LaiFangModule(LaiFangContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public LaiFangPresenter provideLaiFangPresenter(HttpAPIWrapper httpAPIWrapper, LaiFangFragment fragment) {
        return new LaiFangPresenter(httpAPIWrapper, mView, fragment);
    }

    @Provides
    @ActivityScope
    public LaiFangFragment provideLaiFangFragment() {
        return (LaiFangFragment) mView;
    }
}