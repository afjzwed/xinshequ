package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.NormalTypeGoodsListActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.NormalTypeGoodsListModule;

import dagger.Component;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for NormalTypeGoodsListActivity
 * @date 2017/06/16
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = NormalTypeGoodsListModule.class)
public interface NormalTypeGoodsListComponent {
    NormalTypeGoodsListActivity inject(NormalTypeGoodsListActivity Activity);
}