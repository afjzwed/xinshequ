package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.TicketActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.TicketModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for TicketActivity
 * @date 2017/06/22 10:47:40
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = TicketModule.class)
public interface TicketComponent {
    TicketActivity inject(TicketActivity Activity);
}