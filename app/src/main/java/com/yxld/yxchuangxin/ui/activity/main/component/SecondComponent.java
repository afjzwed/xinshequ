package com.yxld.yxchuangxin.ui.activity.main.component;


import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.SecondActivity;
import com.yxld.yxchuangxin.ui.activity.main.module.SecondModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.example.hu.myapplication.ui.activity.main
 * @Description: $description
 * @date 2017/05/18
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = SecondModule.class)
public interface SecondComponent {
    SecondActivity inject(SecondActivity activity);
}