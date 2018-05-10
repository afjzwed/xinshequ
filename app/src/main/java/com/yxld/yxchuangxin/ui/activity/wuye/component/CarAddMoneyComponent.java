package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.CarAddMoneyActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.CarAddMoneyModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for CarAddMoneyActivity
 * @date 2017/07/06 15:05:41
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CarAddMoneyModule.class)
public interface CarAddMoneyComponent {
    CarAddMoneyActivity inject(CarAddMoneyActivity Activity);
}