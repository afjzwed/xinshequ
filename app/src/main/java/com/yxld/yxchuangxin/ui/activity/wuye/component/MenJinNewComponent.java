package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.MenJinNewActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.MenJinNewModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for MenJinNewActivity
 * @date 2018/05/26 11:11:04
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MenJinNewModule.class)
public interface MenJinNewComponent {
    MenJinNewActivity inject(MenJinNewActivity Activity);
}