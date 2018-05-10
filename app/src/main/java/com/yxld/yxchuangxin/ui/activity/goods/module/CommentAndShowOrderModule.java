package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.CommentAndShowOrderActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.CommentAndShowOrderContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.CommentAndShowOrderPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of CommentAndShowOrderActivity, provide field for CommentAndShowOrderActivity
 * @date 2017/06/28 20:14:36
 */
@Module
public class CommentAndShowOrderModule {
    private final CommentAndShowOrderContract.View mView;


    public CommentAndShowOrderModule(CommentAndShowOrderContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CommentAndShowOrderPresenter provideCommentAndShowOrderPresenter(HttpAPIWrapper httpAPIWrapper, CommentAndShowOrderActivity mActivity) {
        return new CommentAndShowOrderPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public CommentAndShowOrderActivity provideCommentAndShowOrderActivity() {
        return (CommentAndShowOrderActivity) mView;
    }
}