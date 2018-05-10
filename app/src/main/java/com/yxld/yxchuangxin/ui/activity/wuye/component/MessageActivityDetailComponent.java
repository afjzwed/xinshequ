package com.yxld.yxchuangxin.ui.activity.wuye.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.MessageActivityDetailActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.module.MessageActivityDetailModule;

import dagger.Component;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The component for MessageActivityDetailActivity
 * @date 2017/06/26 19:57:52
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MessageActivityDetailModule.class)
public interface MessageActivityDetailComponent {
    MessageActivityDetailActivity inject(MessageActivityDetailActivity Activity);
}