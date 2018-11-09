package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.Fkyj2Fragment;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.Fkyj2Contract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.Fkyj2Presenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The moduele of Fkyj2Fragment, provide field for Fkyj2Fragment
 * @date 2018/11/09 13:42:26
 */
@Module
public class Fkyj2Module {
    private final Fkyj2Contract.View mView;


    public Fkyj2Module(Fkyj2Contract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public Fkyj2Presenter provideFkyj2Presenter(HttpAPIWrapper httpAPIWrapper, Fkyj2Fragment mFragment) {
        return new Fkyj2Presenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public Fkyj2Fragment provideFkyj2Fragment() {
        return (Fkyj2Fragment) mView;
    }
}