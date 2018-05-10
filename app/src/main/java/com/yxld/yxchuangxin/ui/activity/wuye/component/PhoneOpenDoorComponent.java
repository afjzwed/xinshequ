package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.PhoneOpenDoorActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.PhoneOpenDoorModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for PhoneOpenDoorActivity
 * @date 2017/06/06
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = PhoneOpenDoorModule.class)
public interface PhoneOpenDoorComponent {
    PhoneOpenDoorActivity inject(PhoneOpenDoorActivity Activity);
}