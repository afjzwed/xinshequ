package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.CymdActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.module.CymdModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The component for CymdActivity
 * @date 2018/11/09 09:18:52
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CymdModule.class)
public interface CymdComponent {
    CymdActivity inject(CymdActivity Activity);
}