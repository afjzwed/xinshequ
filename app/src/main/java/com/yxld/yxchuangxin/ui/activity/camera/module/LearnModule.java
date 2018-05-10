package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.LearnActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.LearnContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.LearnPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of LearnActivity, provide field for LearnActivity
 * @date 2017/06/21 10:22:13
 */
@Module
public class LearnModule {
    private final LearnContract.View mView;


    public LearnModule(LearnContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public LearnPresenter provideLearnPresenter(HttpAPIWrapper httpAPIWrapper, LearnActivity mActivity) {
        return new LearnPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public LearnActivity provideLearnActivity() {
        return (LearnActivity) mView;
    }
}