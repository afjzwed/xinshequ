package com.yxld.yxchuangxin.ui.activity.main.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.FingerLoginActivity;
import com.yxld.yxchuangxin.ui.activity.main.contract.FingerLoginContract;
import com.yxld.yxchuangxin.ui.activity.main.presenter.FingerLoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: The moduele of FingerLoginActivity, provide field for FingerLoginActivity
 * @date 2018/04/03 11:46:25
 */
@Module
public class FingerLoginModule {
    private final FingerLoginContract.View mView;


    public FingerLoginModule(FingerLoginContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FingerLoginPresenter provideFingerLoginPresenter(HttpAPIWrapper httpAPIWrapper, FingerLoginActivity mActivity) {
        return new FingerLoginPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public FingerLoginActivity provideFingerLoginActivity() {
        return (FingerLoginActivity) mView;
    }
}