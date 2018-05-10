package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.AboutOurActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.AboutOurContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.AboutOurPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of AboutOurActivity, provide field for AboutOurActivity
 * @date 2017/06/23 09:20:36
 */
@Module
public class AboutOurModule {
    private final AboutOurContract.View mView;


    public AboutOurModule(AboutOurContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public AboutOurPresenter provideAboutOurPresenter(HttpAPIWrapper httpAPIWrapper, AboutOurActivity mActivity) {
        return new AboutOurPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public AboutOurActivity provideAboutOurActivity() {
        return (AboutOurActivity) mView;
    }
}