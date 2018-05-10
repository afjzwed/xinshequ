package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.ShopCartFragment;
import com.yxld.yxchuangxin.ui.activity.goods.module.ShopCartModule;

import dagger.Component;

/**
 * @author yuan
 * @Package .ui.activity.goods
 * @Description: The component for ShopCartFragment
 * @date 2017/06/14
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ShopCartModule.class)
public interface ShopCartComponent {
    ShopCartFragment inject(ShopCartFragment Fragment);
}