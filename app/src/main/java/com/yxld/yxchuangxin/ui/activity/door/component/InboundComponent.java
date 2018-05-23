package com.yxld.yxchuangxin.ui.activity.door.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.door.InboundActivity;
import com.yxld.yxchuangxin.ui.activity.door.module.InboundModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.door
 * @Description: The component for InboundActivity
 * @date 2018/04/19 14:12:55
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = InboundModule.class)
public interface InboundComponent {
    InboundActivity inject(InboundActivity Activity);
}