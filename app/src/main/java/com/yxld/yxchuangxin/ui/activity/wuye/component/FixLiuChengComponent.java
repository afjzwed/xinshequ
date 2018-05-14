package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.FixLiuChengActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.FixLiuChengModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for FixLiuChengActivity
 * @date 2018/05/14 10:23:41
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FixLiuChengModule.class)
public interface FixLiuChengComponent {
    FixLiuChengActivity inject(FixLiuChengActivity Activity);
}