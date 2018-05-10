package com.yxld.yxchuangxin.ui.activity.main.component;


import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.TestFragment;
import com.yxld.yxchuangxin.ui.activity.main.module.TestModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.example.hu.myapplication.ui.activity.main
 * @Description: $description
 * @date 2017/05/18
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = TestModule.class)
public interface TestComponent {
    TestFragment inject(TestFragment fragment);
}