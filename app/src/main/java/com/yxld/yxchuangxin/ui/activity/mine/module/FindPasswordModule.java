package com.yxld.yxchuangxin.ui.activity.mine.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.mine.FindPasswordActivity;
import com.yxld.yxchuangxin.ui.activity.mine.contract.FindPasswordContract;
import com.yxld.yxchuangxin.ui.activity.mine.presenter.FindPasswordPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: The moduele of FindPasswordActivity, provide field for FindPasswordActivity
 * @date 2017/06/23 14:14:05
 */
@Module
public class FindPasswordModule {
    private final FindPasswordContract.View mView;


    public FindPasswordModule(FindPasswordContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FindPasswordPresenter provideFindPasswordPresenter(HttpAPIWrapper httpAPIWrapper, FindPasswordActivity mActivity) {
        return new FindPasswordPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public FindPasswordActivity provideFindPasswordActivity() {
        return (FindPasswordActivity) mView;
    }
}