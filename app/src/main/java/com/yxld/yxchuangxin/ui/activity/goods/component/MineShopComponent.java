package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.MineShopActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.MineShopModule;

import dagger.Component;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for MineShopActivity
 * @date 2017/10/19 15:23:24
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MineShopModule.class)
public interface MineShopComponent {
    MineShopActivity inject(MineShopActivity Activity);
}