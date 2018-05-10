package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.AddLiveMemberActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.AddLiveMemberModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for AddLiveMemberActivity
 * @date 2017/06/07
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = AddLiveMemberModule.class)
public interface AddLiveMemberComponent {
    AddLiveMemberActivity inject(AddLiveMemberActivity Activity);
}