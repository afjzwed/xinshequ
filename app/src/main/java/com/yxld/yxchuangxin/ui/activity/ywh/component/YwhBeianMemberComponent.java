package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.YwhBeianMemberActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.module.YwhBeianMemberModule;

import dagger.Component;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The component for YwhBeianMemberActivity
 * @date 2018/11/15 09:47:02
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = YwhBeianMemberModule.class)
public interface YwhBeianMemberComponent {
    YwhBeianMemberActivity inject(YwhBeianMemberActivity Activity);
}