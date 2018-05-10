package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.ItemServiceFragment;
import com.yxld.yxchuangxin.ui.activity.rim.module.ItemServiceModule;

import dagger.Component;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The component for ItemServiceFragment
 * @date 2017/06/16
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ItemServiceModule.class)
public interface ItemServiceComponent {
    ItemServiceFragment inject(ItemServiceFragment Fragment);
}