package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.Fkyj1Fragment;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.Fkyj1Contract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.Fkyj1Presenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The moduele of Fkyj1Fragment, provide field for Fkyj1Fragment
 * @date 2018/11/09 13:26:28
 */
@Module
public class Fkyj1Module {
    private final Fkyj1Contract.View mView;


    public Fkyj1Module(Fkyj1Contract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public Fkyj1Presenter provideFkyj1Presenter(HttpAPIWrapper httpAPIWrapper, Fkyj1Fragment mFragment) {
        return new Fkyj1Presenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public Fkyj1Fragment provideFkyj1Fragment() {
        return (Fkyj1Fragment) mView;
    }
}