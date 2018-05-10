package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.SecondShopCartActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.SecondShopCartModule;

import dagger.Component;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for SecondShopCartActivity
 * @date 2017/07/05 14:05:30
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = SecondShopCartModule.class)
public interface SecondShopCartComponent {
    SecondShopCartActivity inject(SecondShopCartActivity Activity);
}