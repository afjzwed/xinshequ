package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.FixListActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.FixListContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.FixListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of FixListActivity, provide field for FixListActivity
 * @date 2017/06/15
 */
@Module
public class FixListModule {
    private final FixListContract.View mView;


    public FixListModule(FixListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FixListPresenter provideFixListPresenter(HttpAPIWrapper httpAPIWrapper, FixListActivity activity) {
        return new FixListPresenter(httpAPIWrapper, mView, activity);
    }

    @Provides
    @ActivityScope
    public FixListActivity provideFixListActivity() {
        return (FixListActivity) mView;
    }
}