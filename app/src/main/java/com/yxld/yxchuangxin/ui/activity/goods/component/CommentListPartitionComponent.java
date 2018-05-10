package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.CommentListPartitionFragment;
import com.yxld.yxchuangxin.ui.activity.goods.module.CommentListPartitionModule;

import dagger.Component;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for CommentListPartitionFragment
 * @date 2017/06/30 16:43:22
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CommentListPartitionModule.class)
public interface CommentListPartitionComponent {
    CommentListPartitionFragment inject(CommentListPartitionFragment Fragment);
}