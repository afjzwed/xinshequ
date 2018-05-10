package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.CommentListPartitionFragment;
import com.yxld.yxchuangxin.ui.activity.goods.contract.CommentListPartitionContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.CommentListPartitionPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of CommentListPartitionFragment, provide field for CommentListPartitionFragment
 * @date 2017/06/30 16:43:22
 */
@Module
public class CommentListPartitionModule {
    private final CommentListPartitionContract.View mView;


    public CommentListPartitionModule(CommentListPartitionContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CommentListPartitionPresenter provideCommentListPartitionPresenter(HttpAPIWrapper httpAPIWrapper, CommentListPartitionFragment mFragment) {
        return new CommentListPartitionPresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public CommentListPartitionFragment provideCommentListPartitionFragment() {
        return (CommentListPartitionFragment) mView;
    }
}