package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.ComplainActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.ComplainContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.ComplainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of ComplainActivity, provide field for ComplainActivity
 * @date 2017/06/20 09:58:43
 */
@Module
public class ComplainModule {
    private final ComplainContract.View mView;


    public ComplainModule(ComplainContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ComplainPresenter provideComplainPresenter(HttpAPIWrapper httpAPIWrapper, ComplainActivity mActivity) {
        return new ComplainPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public ComplainActivity provideComplainActivity() {
        return (ComplainActivity) mView;
    }
}