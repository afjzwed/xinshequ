package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimCommentListActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimCommentListContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimCommentListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The moduele of RimCommentListActivity, provide field for RimCommentListActivity
 * @date 2017/06/17
 */
@Module
public class RimCommentListModule {
    private final RimCommentListContract.View mView;


    public RimCommentListModule(RimCommentListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RimCommentListPresenter provideRimCommentListPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new RimCommentListPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public RimCommentListActivity provideRimCommentListActivity() {
        return (RimCommentListActivity) mView;
    }

}