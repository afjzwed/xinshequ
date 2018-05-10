package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.WuyeMoneyActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.WuyeMoneyModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for WuyeMoneyActivity
 * @date 2017/06/06
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = WuyeMoneyModule.class)
public interface WuyeMoneyComponent {
    WuyeMoneyActivity inject(WuyeMoneyActivity Activity);
}