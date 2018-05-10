package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.PayWaySelectActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.PayWaySelectModule;
import com.yxld.yxchuangxin.ui.activity.goods.module.SearchModule;

import dagger.Component;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/29
 * @descprition:
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = PayWaySelectModule.class)
public interface PayWaySelectComponent {
    PayWaySelectActivity inject(PayWaySelectActivity Activity);
}
