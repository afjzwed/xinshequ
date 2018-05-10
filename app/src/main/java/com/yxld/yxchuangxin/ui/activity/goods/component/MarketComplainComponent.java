package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.MarketComplainActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.MarketComplainModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for MarketComplainActivity
 * @date 2017/06/22 15:30:24
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MarketComplainModule.class)
public interface MarketComplainComponent {
    MarketComplainActivity inject(MarketComplainActivity Activity);
}