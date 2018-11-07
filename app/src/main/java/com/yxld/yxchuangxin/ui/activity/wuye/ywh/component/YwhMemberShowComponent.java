package com.yxld.yxchuangxin.ui.activity.wuye.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.ywh.YwhMemberShowActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.ywh.module.YwhMemberShowModule;

import dagger.Component;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for YwhMemberShowActivity
 * @date 2018/11/07 20:37:02
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = YwhMemberShowModule.class)
public interface YwhMemberShowComponent {
    YwhMemberShowActivity inject(YwhMemberShowActivity Activity);
}