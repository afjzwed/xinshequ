package com.yxld.yxchuangxin.ui.activity.ywh.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.CheckNoticeActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.module.CheckNoticeModule;

import dagger.Component;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The component for CheckNoticeActivity
 * @date 2018/11/08 17:11:57
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CheckNoticeModule.class)
public interface CheckNoticeComponent {
    CheckNoticeActivity inject(CheckNoticeActivity Activity);
}