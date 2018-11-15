package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.YwhBeianMemberActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.YwhBeianMemberContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.YwhBeianMemberPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The moduele of YwhBeianMemberActivity, provide field for YwhBeianMemberActivity
 * @date 2018/11/15 09:47:02
 */
@Module
public class YwhBeianMemberModule {
    private final YwhBeianMemberContract.View mView;


    public YwhBeianMemberModule(YwhBeianMemberContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public YwhBeianMemberPresenter provideYwhBeianMemberPresenter(HttpAPIWrapper httpAPIWrapper, YwhBeianMemberActivity mActivity) {
        return new YwhBeianMemberPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public YwhBeianMemberActivity provideYwhBeianMemberActivity() {
        return (YwhBeianMemberActivity) mView;
    }
}