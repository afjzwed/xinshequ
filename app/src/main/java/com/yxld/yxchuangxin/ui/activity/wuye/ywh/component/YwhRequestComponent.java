package com.yxld.yxchuangxin.ui.activity.wuye.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.ywh.YwhRequestActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.ywh.module.YwhRequestModule;

import dagger.Component;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for YwhRequestActivity
 * @date 2018/11/07 18:59:19
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = YwhRequestModule.class)
public interface YwhRequestComponent {
    YwhRequestActivity inject(YwhRequestActivity Activity);
}