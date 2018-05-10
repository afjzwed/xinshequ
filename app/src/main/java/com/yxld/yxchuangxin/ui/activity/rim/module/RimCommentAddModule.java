package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimCommentAddActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimCommentAddContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimCommentAddPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The moduele of RimCommentAddActivity, provide field for RimCommentAddActivity
 * @date 2017/06/17
 */
@Module
public class RimCommentAddModule {
    private final RimCommentAddContract.View mView;


    public RimCommentAddModule(RimCommentAddContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RimCommentAddPresenter provideRimCommentAddPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new RimCommentAddPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public RimCommentAddActivity provideRimCommentAddActivity() {
        return (RimCommentAddActivity) mView;
    }
}