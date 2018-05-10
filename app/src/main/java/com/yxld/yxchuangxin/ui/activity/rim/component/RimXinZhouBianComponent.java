package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimXinZhouBianFragment;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimXinZhouBianModule;

import dagger.Component;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The component for RimXinZhouBianFragment
 * @date 2017/06/16
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RimXinZhouBianModule.class)
public interface RimXinZhouBianComponent {
    RimXinZhouBianFragment inject(RimXinZhouBianFragment Fragment);
}