package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.RoomRentActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.RoomRentModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for RoomRentActivity
 * @date 2017/06/16
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RoomRentModule.class)
public interface RoomRentComponent {
    RoomRentActivity inject(RoomRentActivity Activity);
}