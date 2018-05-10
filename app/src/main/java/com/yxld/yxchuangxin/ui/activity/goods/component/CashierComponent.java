package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.CashierActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.CashierModule;

import dagger.Component;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for CashierActivity
 * @date 2017/06/27 19:49:09
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CashierModule.class)
public interface CashierComponent {
    CashierActivity inject(CashierActivity Activity);
}