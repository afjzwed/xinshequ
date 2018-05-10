package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.ComplainListActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.ComplainListModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for ComplainListActivity
 * @date 2017/06/20 13:31:40
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ComplainListModule.class)
public interface ComplainListComponent {
    ComplainListActivity inject(ComplainListActivity Activity);
}