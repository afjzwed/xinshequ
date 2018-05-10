package com.yxld.yxchuangxin.ui.activity.main.component;


import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.FragmentActivityActivity;
import com.yxld.yxchuangxin.ui.activity.main.module.FragmentActivityModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.example.hu.myapplication.ui.activity.main
 * @Description: The component for FragmentActivityActivity
 * @date 2017/05/18
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FragmentActivityModule.class)
public interface FragmentActivityComponent {
    FragmentActivityActivity inject(FragmentActivityActivity activity);
}