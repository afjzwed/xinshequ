package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.MallGoodsListActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.MallGoodsListModule;

import dagger.Component;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for MallGoodsListActivity
 * @date 2017/06/19 14:28:26
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MallGoodsListModule.class)
public interface MallGoodsListComponent {
    MallGoodsListActivity inject(MallGoodsListActivity Activity);
}