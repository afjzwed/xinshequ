package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.OrderComplainActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.OrderComplainModule;

import dagger.Component;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for OrderComplainActivity
 * @date 2017/06/22 18:01:39
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = OrderComplainModule.class)
public interface OrderComplainComponent {
    OrderComplainActivity inject(OrderComplainActivity Activity);
}