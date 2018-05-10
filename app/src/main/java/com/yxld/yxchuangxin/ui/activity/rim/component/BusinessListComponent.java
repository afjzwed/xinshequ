package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.BusinessListActivity;
import com.yxld.yxchuangxin.ui.activity.rim.module.BusinessListModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The component for BusinessListActivity
 * @date 2017/06/16
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = BusinessListModule.class)
public interface BusinessListComponent {
    BusinessListActivity inject(BusinessListActivity Activity);
}