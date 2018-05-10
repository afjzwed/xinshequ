package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.MenJinActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.MenJinModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for MenJinActivity
 * @date 2017/06/06
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MenJinModule.class)
public interface MenJinComponent {
    MenJinActivity inject(MenJinActivity Activity);
}