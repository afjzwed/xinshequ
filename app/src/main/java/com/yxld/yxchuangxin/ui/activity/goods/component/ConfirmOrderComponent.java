package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.ConfirmOrderActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.ConfirmOrderModule;

import dagger.Component;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for ConfirmOrderActivity
 * @date 2017/06/22 11:07:51
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ConfirmOrderModule.class)
public interface ConfirmOrderComponent {
    ConfirmOrderActivity inject(ConfirmOrderActivity Activity);
}