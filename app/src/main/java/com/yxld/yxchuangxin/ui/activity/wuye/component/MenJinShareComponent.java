package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.MenJinShareFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.module.MenJinShareModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for MenJinShareFragment
 * @date 2018/05/26 13:36:35
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MenJinShareModule.class)
public interface MenJinShareComponent {
    MenJinShareFragment inject(MenJinShareFragment Fragment);
}