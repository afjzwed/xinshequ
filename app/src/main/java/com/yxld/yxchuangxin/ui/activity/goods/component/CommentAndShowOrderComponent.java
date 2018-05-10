package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.CommentAndShowOrderActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.CommentAndShowOrderModule;

import dagger.Component;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for CommentAndShowOrderActivity
 * @date 2017/06/28 20:14:36
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CommentAndShowOrderModule.class)
public interface CommentAndShowOrderComponent {
    CommentAndShowOrderActivity inject(CommentAndShowOrderActivity Activity);
}