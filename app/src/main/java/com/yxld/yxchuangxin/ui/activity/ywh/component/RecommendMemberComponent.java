package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.RecommendMemberActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.module.RecommendMemberModule;

import dagger.Component;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for RecommendMemberActivity
 * @date 2018/11/07 19:36:36
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RecommendMemberModule.class)
public interface RecommendMemberComponent {
    RecommendMemberActivity inject(RecommendMemberActivity Activity);
}