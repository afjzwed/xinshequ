package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.MarketComplainListActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.MarketComplainListModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for MarketComplainListActivity
 * @date 2017/11/02 14:28:33
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MarketComplainListModule.class)
public interface MarketComplainListComponent {
    MarketComplainListActivity inject(MarketComplainListActivity Activity);
}