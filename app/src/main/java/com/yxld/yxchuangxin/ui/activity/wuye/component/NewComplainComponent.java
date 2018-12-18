package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.NewComplainActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.NewComplainModule;

import dagger.Component;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for NewComplainActivity
 * @date 2018/12/14 10:49:02
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = NewComplainModule.class)
public interface NewComplainComponent {
    NewComplainActivity inject(NewComplainActivity Activity);
}