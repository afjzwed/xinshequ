package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.UpdateActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.UpdateContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.UpdatePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of UpdateActivity, provide field for UpdateActivity
 * @date 2017/06/23 09:25:59
 */
@Module
public class UpdateModule {
    private final UpdateContract.View mView;


    public UpdateModule(UpdateContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public UpdatePresenter provideUpdatePresenter(HttpAPIWrapper httpAPIWrapper, UpdateActivity mActivity) {
        return new UpdatePresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public UpdateActivity provideUpdateActivity() {
        return (UpdateActivity) mView;
    }
}