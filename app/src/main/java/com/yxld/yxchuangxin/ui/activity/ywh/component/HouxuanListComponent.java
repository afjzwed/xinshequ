package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.HouxuanListActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.module.HouxuanListModule;

import dagger.Component;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The component for HouxuanListActivity
 * @date 2018/11/14 09:53:34
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = HouxuanListModule.class)
public interface HouxuanListComponent {
    HouxuanListActivity inject(HouxuanListActivity Activity);
}