package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.ComplainActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.ComplainModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for ComplainActivity
 * @date 2017/06/20 09:58:43
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ComplainModule.class)
public interface ComplainComponent {
    ComplainActivity inject(ComplainActivity Activity);
}