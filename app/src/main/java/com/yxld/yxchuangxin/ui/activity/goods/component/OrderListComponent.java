package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.OrderListActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.OrderListModule;

import dagger.Component;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for OrderListActivity
 * @date 2017/06/21 17:03:40
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = OrderListModule.class)
public interface OrderListComponent {
    OrderListActivity inject(OrderListActivity Activity);
}