package com.yxld.yxchuangxin.ui.activity.main.module;


import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.TwoActivity;
import com.yxld.yxchuangxin.ui.activity.main.contract.TwoContract;
import com.yxld.yxchuangxin.ui.activity.main.presenter.TwoPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.example.hu.myapplication.ui.activity.main
 * @Description: $description
 * @date 2017/05/18
 */
@Module
public class TwoModule {
    private final TwoContract.View mView;


    public TwoModule(TwoContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public TwoPresenter provideTwoPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new TwoPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public TwoActivity provideTwoActivity() {
        return (TwoActivity) mView;
    }
}