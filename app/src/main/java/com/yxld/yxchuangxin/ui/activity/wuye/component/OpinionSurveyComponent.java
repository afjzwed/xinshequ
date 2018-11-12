package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.OpinionSurveyActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.OpinionSurveyModule;

import dagger.Component;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for OpinionSurveyActivity
 * @date 2018/11/12 18:08:34
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = OpinionSurveyModule.class)
public interface OpinionSurveyComponent {
    OpinionSurveyActivity inject(OpinionSurveyActivity Activity);
}