package com.yxld.yxchuangxin.ui.activity.mine.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.mine.AddAccountActivity;
import com.yxld.yxchuangxin.ui.activity.mine.contract.AddAccountContract;
import com.yxld.yxchuangxin.ui.activity.mine.presenter.AddAccountPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: The moduele of AddAccountActivity, provide field for AddAccountActivity
 * @date 2017/10/11 09:49:50
 */
@Module
public class AddAccountModule {
    private final AddAccountContract.View mView;


    public AddAccountModule(AddAccountContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public AddAccountPresenter provideAddAccountPresenter(HttpAPIWrapper httpAPIWrapper, AddAccountActivity mActivity) {
        return new AddAccountPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public AddAccountActivity provideAddAccountActivity() {
        return (AddAccountActivity) mView;
    }
}