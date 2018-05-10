package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.MyEvaluateActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.MyEvaluateModule;

import dagger.Component;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for MyEvaluateActivity
 * @date 2017/10/23 13:55:27
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MyEvaluateModule.class)
public interface MyEvaluateComponent {
    MyEvaluateActivity inject(MyEvaluateActivity Activity);
}