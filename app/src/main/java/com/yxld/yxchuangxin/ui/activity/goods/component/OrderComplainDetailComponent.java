package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.OrderComplainDetailActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.OrderComplainDetailModule;

import dagger.Component;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for OrderComplainDetailActivity
 * @date 2017/07/05 14:52:21
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = OrderComplainDetailModule.class)
public interface OrderComplainDetailComponent {
    OrderComplainDetailActivity inject(OrderComplainDetailActivity Activity);
}