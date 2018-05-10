package com.yxld.yxchuangxin.ui.activity.mine.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.mine.ResetPasswordActivity;
import com.yxld.yxchuangxin.ui.activity.mine.contract.ResetPasswordContract;
import com.yxld.yxchuangxin.ui.activity.mine.presenter.ResetPasswordPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: The moduele of ResetPasswordActivity, provide field for ResetPasswordActivity
 * @date 2017/06/23 11:03:59
 */
@Module
public class ResetPasswordModule {
    private final ResetPasswordContract.View mView;


    public ResetPasswordModule(ResetPasswordContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ResetPasswordPresenter provideResetPasswordPresenter(HttpAPIWrapper httpAPIWrapper, ResetPasswordActivity mActivity) {
        return new ResetPasswordPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public ResetPasswordActivity provideResetPasswordActivity() {
        return (ResetPasswordActivity) mView;
    }
}