package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.MarketFragment;
import com.yxld.yxchuangxin.ui.activity.goods.module.MarketModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for MarketActivity
 * @date 2017/06/12
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MarketModule.class)
public interface MarketComponent {
    MarketFragment inject(MarketFragment Activity);
}