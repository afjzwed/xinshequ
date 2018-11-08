package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.ResultShowActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.module.ResultShowModule;

import dagger.Component;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for ResultShowActivity
 * @date 2018/11/07 20:09:47
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ResultShowModule.class)
public interface ResultShowComponent {
    ResultShowActivity inject(ResultShowActivity Activity);
}