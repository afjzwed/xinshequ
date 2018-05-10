package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.AddressManageActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.AddressManageModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for AddressManageActivity
 * @date 2017/06/22 17:20:34
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = AddressManageModule.class)
public interface AddressManageComponent {
    AddressManageActivity inject(AddressManageActivity Activity);
}