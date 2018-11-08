package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.YwhMemberShowActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.YwhMemberShowContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.YwhMemberShowPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of YwhMemberShowActivity, provide field for YwhMemberShowActivity
 * @date 2018/11/07 20:37:02
 */
@Module
public class YwhMemberShowModule {
    private final YwhMemberShowContract.View mView;


    public YwhMemberShowModule(YwhMemberShowContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public YwhMemberShowPresenter provideYwhMemberShowPresenter(HttpAPIWrapper httpAPIWrapper, YwhMemberShowActivity mActivity) {
        return new YwhMemberShowPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public YwhMemberShowActivity provideYwhMemberShowActivity() {
        return (YwhMemberShowActivity) mView;
    }
}