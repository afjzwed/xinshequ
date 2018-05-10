package com.yxld.yxchuangxin.ui.activity.main.module;


import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.TestFragment;
import com.yxld.yxchuangxin.ui.activity.main.contract.TestContract;
import com.yxld.yxchuangxin.ui.activity.main.presenter.TestPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.example.hu.myapplication.ui.activity.main
 * @Description: $description
 * @date 2017/05/18
 */
@Module
public class TestModule {
    private final TestContract.View mView;


    public TestModule(TestContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public TestPresenter provideTwoPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new TestPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public TestFragment provideTestFragment() {
        return (TestFragment) mView;
    }
}