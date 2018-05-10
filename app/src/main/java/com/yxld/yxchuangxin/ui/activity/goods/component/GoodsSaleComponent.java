package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.GoodsSaleActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.GoodsSaleModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for GoodsSaleActivity
 * @date 2017/10/23 10:36:05
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = GoodsSaleModule.class)
public interface GoodsSaleComponent {
    GoodsSaleActivity inject(GoodsSaleActivity Activity);
}