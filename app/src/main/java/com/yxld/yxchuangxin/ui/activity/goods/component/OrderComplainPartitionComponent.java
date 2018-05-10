package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.OrderComplainPartitionFragment;
import com.yxld.yxchuangxin.ui.activity.goods.module.OrderComplainPartitionModule;

import dagger.Component;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for OrderComplainPartitionFragment
 * @date 2017/06/22 18:31:29
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = OrderComplainPartitionModule.class)
public interface OrderComplainPartitionComponent {
    OrderComplainPartitionFragment inject(OrderComplainPartitionFragment Fragment);
}