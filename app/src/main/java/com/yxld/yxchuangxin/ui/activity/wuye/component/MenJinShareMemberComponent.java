package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.MenJinShareMemberActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.MenJinShareMemberModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for MenJinShareMemberActivity
 * @date 2018/06/11 14:53:04
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MenJinShareMemberModule.class)
public interface MenJinShareMemberComponent {
    MenJinShareMemberActivity inject(MenJinShareMemberActivity Activity);
}