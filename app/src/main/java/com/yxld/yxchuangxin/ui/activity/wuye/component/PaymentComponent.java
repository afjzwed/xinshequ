package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.PaymentFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.module.PaymentModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for PaymentFragment
 * @date 2017/07/01 13:45:55
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = PaymentModule.class)
public interface PaymentComponent {
    PaymentFragment inject(PaymentFragment Fragment);
}