package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.CarMoneyRecordActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.CarMoneyRecordModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for CarMoneyRecordActivity
 * @date 2017/07/06 18:00:14
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CarMoneyRecordModule.class)
public interface CarMoneyRecordComponent {
    CarMoneyRecordActivity inject(CarMoneyRecordActivity Activity);
}