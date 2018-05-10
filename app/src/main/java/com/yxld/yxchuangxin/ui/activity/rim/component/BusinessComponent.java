package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.BusinessActivity;
import com.yxld.yxchuangxin.ui.activity.rim.module.BusinessModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The component for BusinessActivity
 * @date 2017/06/17
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = BusinessModule.class)
public interface BusinessComponent {
    BusinessActivity inject(BusinessActivity Activity);
}