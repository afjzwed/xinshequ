package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.BusinessOrderDetailActivity;
import com.yxld.yxchuangxin.ui.activity.rim.module.BusinessOrderDetailModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The component for BusinessOrderDetailActivity
 * @date 2017/06/20 14:05:57
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = BusinessOrderDetailModule.class)
public interface BusinessOrderDetailComponent {
    BusinessOrderDetailActivity inject(BusinessOrderDetailActivity Activity);
}