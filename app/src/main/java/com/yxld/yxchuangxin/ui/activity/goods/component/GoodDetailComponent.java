package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.GoodDetailActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.GoodDetailModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for GoodDetailActivity
 * @date 2017/06/16
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = GoodDetailModule.class)
public interface GoodDetailComponent {
    GoodDetailActivity inject(GoodDetailActivity Activity);
}