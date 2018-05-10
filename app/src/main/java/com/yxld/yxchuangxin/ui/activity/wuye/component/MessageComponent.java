package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.MessageFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.module.MessageModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for MessageFragment
 * @date 2017/06/14
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MessageModule.class)
public interface MessageComponent {
    MessageFragment inject(MessageFragment Fragment);
}