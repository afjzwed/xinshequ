package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.OrderDetailActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.OrderDetailModule;

import dagger.Component;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for OrderDetailActivity
 * @date 2017/06/22 15:45:54
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = OrderDetailModule.class)
public interface OrderDetailComponent {
    OrderDetailActivity inject(OrderDetailActivity Activity);
}