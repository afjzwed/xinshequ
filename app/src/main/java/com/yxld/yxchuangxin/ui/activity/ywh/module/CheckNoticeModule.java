package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.CheckNoticeActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.CheckNoticeContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.CheckNoticePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The moduele of CheckNoticeActivity, provide field for CheckNoticeActivity
 * @date 2018/11/08 17:11:57
 */
@Module
public class CheckNoticeModule {
    private final CheckNoticeContract.View mView;


    public CheckNoticeModule(CheckNoticeContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CheckNoticePresenter provideCheckNoticePresenter(HttpAPIWrapper httpAPIWrapper, CheckNoticeActivity mActivity) {
        return new CheckNoticePresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public CheckNoticeActivity provideCheckNoticeActivity() {
        return (CheckNoticeActivity) mView;
    }
}