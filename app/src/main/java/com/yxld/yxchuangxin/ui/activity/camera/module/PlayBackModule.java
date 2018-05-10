package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.PlayBackActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.PlayBackContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.PlayBackPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of PlayBackActivity, provide field for PlayBackActivity
 * @date 2017/06/21 10:22:52
 */
@Module
public class PlayBackModule {
    private final PlayBackContract.View mView;


    public PlayBackModule(PlayBackContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public PlayBackPresenter providePlayBackPresenter(HttpAPIWrapper httpAPIWrapper, PlayBackActivity mActivity) {
        return new PlayBackPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public PlayBackActivity providePlayBackActivity() {
        return (PlayBackActivity) mView;
    }
}