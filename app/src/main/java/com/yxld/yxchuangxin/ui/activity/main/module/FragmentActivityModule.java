package com.yxld.yxchuangxin.ui.activity.main.module;


import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.FragmentActivityActivity;
import com.yxld.yxchuangxin.ui.activity.main.contract.FragmentActivityContract;
import com.yxld.yxchuangxin.ui.activity.main.presenter.FragmentActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.example.hu.myapplication.ui.activity.main
 * @Description: The moduele of FragmentActivityActivity, provide field for FragmentActivityActivity
 * @date 2017/05/18
 */
@Module
public class FragmentActivityModule {
    private final FragmentActivityContract.View mView;


    public FragmentActivityModule(FragmentActivityContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FragmentActivityPresenter provideFragmentActivityPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new FragmentActivityPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public FragmentActivityActivity provideFragmentActivityActivity() {
        return (FragmentActivityActivity) mView;
    }
}