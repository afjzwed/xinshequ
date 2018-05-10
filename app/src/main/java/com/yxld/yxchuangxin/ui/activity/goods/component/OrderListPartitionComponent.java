package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.OrderListPartitionFragment;
import com.yxld.yxchuangxin.ui.activity.goods.module.OrderListPartitionModule;

import dagger.Component;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for OrderListPartitionFragment
 * @date 2017/06/21 17:10:04
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = OrderListPartitionModule.class)
public interface OrderListPartitionComponent {
    OrderListPartitionFragment inject(OrderListPartitionFragment Fragment);
}