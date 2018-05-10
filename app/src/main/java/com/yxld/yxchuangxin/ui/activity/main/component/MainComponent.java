package com.yxld.yxchuangxin.ui.activity.main.component;


import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.MainFragment;
import com.yxld.yxchuangxin.ui.activity.main.module.Mainmodule;

import dagger.Component;

/**
 * Created by hu on 2017/5/16.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = Mainmodule.class)
public interface MainComponent {
    MainFragment inject(MainFragment activity);
}
