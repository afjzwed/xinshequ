package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimShopDetailActivity;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimShopDetailModule;

import dagger.Component;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.rimshopdetail
 * @Description: The component for RimShopDetailActivity
 * @date 2017/12/15 12:04:35
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RimShopDetailModule.class)
public interface RimShopDetailComponent {
    RimShopDetailActivity inject(RimShopDetailActivity Activity);
}