package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.CommentListActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.CommentListModule;

import dagger.Component;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for CommentListActivity
 * @date 2017/06/29 11:01:08
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CommentListModule.class)
public interface CommentListComponent {
    CommentListActivity inject(CommentListActivity Activity);
}