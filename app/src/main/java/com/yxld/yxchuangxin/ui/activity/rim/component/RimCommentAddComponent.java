package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimCommentAddActivity;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimCommentAddModule;

import dagger.Component;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The component for RimCommentAddActivity
 * @date 2017/06/17
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RimCommentAddModule.class)
public interface RimCommentAddComponent {
    RimCommentAddActivity inject(RimCommentAddActivity Activity);
}