package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.FangxingDetaillActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.FangxingDetaillModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for FangxingDetaillActivity
 * @date 2017/06/14
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FangxingDetaillModule.class)
public interface FangxingDetaillComponent {
    FangxingDetaillActivity inject(FangxingDetaillActivity Activity);
}