package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.TuiJianListActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.module.TuiJianListModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The component for TuiJianListActivity
 * @date 2018/11/08 10:54:14
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = TuiJianListModule.class)
public interface TuiJianListComponent {
    TuiJianListActivity inject(TuiJianListActivity Activity);
}