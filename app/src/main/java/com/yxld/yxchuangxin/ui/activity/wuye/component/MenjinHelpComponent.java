package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.MenjinHelpActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.MenjinHelpModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for MenjinHelpActivity
 * @date 2017/06/26 15:48:18
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MenjinHelpModule.class)
public interface MenjinHelpComponent {
    MenjinHelpActivity inject(MenjinHelpActivity Activity);
}