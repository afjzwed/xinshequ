package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.PqrzResultActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.module.PqrzResultModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The component for PqrzResultActivity
 * @date 2018/11/09 14:52:55
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = PqrzResultModule.class)
public interface PqrzResultComponent {
    PqrzResultActivity inject(PqrzResultActivity Activity);
}