package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimCommentListActivity;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimCommentListModule;

import dagger.Component;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The component for RimCommentListActivity
 * @date 2017/06/17
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RimCommentListModule.class)
public interface RimCommentListComponent {
    RimCommentListActivity inject(RimCommentListActivity Activity);
}