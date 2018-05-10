package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.BusinessPushProductActivity;
import com.yxld.yxchuangxin.ui.activity.rim.module.BusinessPushProductModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The component for BusinessPushProductActivity
 * @date 2017/06/19 11:24:46
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = BusinessPushProductModule.class)
public interface BusinessPushProductComponent {
    BusinessPushProductActivity inject(BusinessPushProductActivity Activity);
}