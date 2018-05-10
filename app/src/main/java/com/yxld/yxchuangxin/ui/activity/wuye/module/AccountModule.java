package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.AccountActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.AccountContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.AccountPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of AccountActivity, provide field for AccountActivity
 * @date 2017/06/06
 */
@Module
public class AccountModule {
    private final AccountContract.View mView;


    public AccountModule(AccountContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public AccountPresenter provideAccountPresenter(HttpAPIWrapper httpAPIWrapper, AccountActivity activity) {
        return new AccountPresenter(httpAPIWrapper, mView, activity);
    }

    @Provides
    @ActivityScope
    public AccountActivity provideAccountActivity() {
        return (AccountActivity) mView;
    }
}