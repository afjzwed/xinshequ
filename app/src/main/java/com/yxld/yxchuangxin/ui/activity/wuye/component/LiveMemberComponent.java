package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.LiveMemberActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.LiveMemberModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for LiveMemberActivity
 * @date 2017/06/07
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = LiveMemberModule.class)
public interface LiveMemberComponent {
    LiveMemberActivity inject(LiveMemberActivity Activity);
}