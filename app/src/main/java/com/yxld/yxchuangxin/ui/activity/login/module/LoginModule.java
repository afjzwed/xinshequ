package com.yxld.yxchuangxin.ui.activity.login.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.login.LoginActivity;
import com.yxld.yxchuangxin.ui.activity.login.contract.LoginContract;
import com.yxld.yxchuangxin.ui.activity.login.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.login
 * @Description: The moduele of LoginActivity, provide field for LoginActivity
 * @date 2017/05/23
 */
@Module
public class LoginModule {
    private final LoginContract.View mView;


    public LoginModule(LoginContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public LoginPresenter provideLoginPresenter(HttpAPIWrapper httpAPIWrapper, LoginActivity mActivity) {
        return new LoginPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public LoginActivity provideLoginActivity() {
        return (LoginActivity) mView;
    }
}