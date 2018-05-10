package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.AddAddressActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.AddAddressModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for AddAddressActivity
 * @date 2017/06/22 17:36:39
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = AddAddressModule.class)
public interface AddAddressComponent {
    AddAddressActivity inject(AddAddressActivity Activity);
}