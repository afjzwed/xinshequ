package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.PqrzActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.module.PqrzModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The component for PqrzActivity
 * @date 2018/11/09 08:55:03
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = PqrzModule.class)
public interface PqrzComponent {
    PqrzActivity inject(PqrzActivity Activity);
}