package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.MessageActivityActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.MessageActivityModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for MessageActivityActivity
 * @date 2017/06/14
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MessageActivityModule.class)
public interface MessageActivityComponent {
    MessageActivityActivity inject(MessageActivityActivity Activity);
}