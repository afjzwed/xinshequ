package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.YeZhuFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.YeZhuContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.YeZhuPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of YeZhuFragment, provide field for YeZhuFragment
 * @date 2017/06/06
 */
@Module
public class YeZhuModule {
    private final YeZhuContract.View mView;


    public YeZhuModule(YeZhuContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public YeZhuPresenter provideYeZhuPresenter(HttpAPIWrapper httpAPIWrapper, YeZhuFragment yeZhuFragment) {
        return new YeZhuPresenter(httpAPIWrapper, mView, yeZhuFragment);
    }

    @Provides
    @ActivityScope
    public YeZhuFragment provideYeZhuFragment() {
        return (YeZhuFragment) mView;
    }
}