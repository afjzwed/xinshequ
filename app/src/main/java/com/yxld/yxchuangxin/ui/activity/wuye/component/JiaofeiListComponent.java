package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.JiaofeiListActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.JiaofeiListModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for JiaofeiListActivity
 * @date 2017/07/01 13:34:37
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = JiaofeiListModule.class)
public interface JiaofeiListComponent {
    JiaofeiListActivity inject(JiaofeiListActivity Activity);
}