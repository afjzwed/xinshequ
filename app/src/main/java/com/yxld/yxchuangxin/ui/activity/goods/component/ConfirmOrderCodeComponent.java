package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.ConfirmOrderCodeActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.ConfirmOrderCodeModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for ConfirmOrderCodeActivity
 * @date 2018/03/21 08:46:31
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ConfirmOrderCodeModule.class)
public interface ConfirmOrderCodeComponent {
    ConfirmOrderCodeActivity inject(ConfirmOrderCodeActivity Activity);
}