package com.yxld.yxchuangxin.ui.activity.main.component;


import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.TwoActivity;
import com.yxld.yxchuangxin.ui.activity.main.module.TwoModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.example.hu.myapplication.ui.activity.main
 * @Description: $description
 * @date 2017/05/18
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = TwoModule.class)
public interface TwoComponent {
    TwoActivity inject(TwoActivity activity);
}