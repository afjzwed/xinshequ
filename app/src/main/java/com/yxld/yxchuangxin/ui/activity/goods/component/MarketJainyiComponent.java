package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.MarketJainyiActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.MarketJainyiModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for MarketJainyiActivity
 * @date 2017/06/22 16:19:40
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MarketJainyiModule.class)
public interface MarketJainyiComponent {
    MarketJainyiActivity inject(MarketJainyiActivity Activity);
}