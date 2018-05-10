package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.CommentListActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.CommentListContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.CommentListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of CommentListActivity, provide field for CommentListActivity
 * @date 2017/06/29 11:01:08
 */
@Module
public class CommentListModule {
    private final CommentListContract.View mView;


    public CommentListModule(CommentListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CommentListPresenter provideCommentListPresenter(HttpAPIWrapper httpAPIWrapper, CommentListActivity mActivity) {
        return new CommentListPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public CommentListActivity provideCommentListActivity() {
        return (CommentListActivity) mView;
    }
}