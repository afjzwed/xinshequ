package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.MenJinListFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.module.MenJinListModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for MenJinListFragment
 * @date 2018/05/26 13:35:55
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MenJinListModule.class)
public interface MenJinListComponent {
    MenJinListFragment inject(MenJinListFragment Fragment);
}