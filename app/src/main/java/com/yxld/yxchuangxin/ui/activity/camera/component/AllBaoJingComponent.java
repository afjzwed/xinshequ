package com.yxld.yxchuangxin.ui.activity.camera.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.AllBaoJingActivity;
import com.yxld.yxchuangxin.ui.activity.camera.module.AllBaoJingModule;

import dagger.Component;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The component for AllBaoJingActivity
 * @date 2017/09/07 01:26:53
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = AllBaoJingModule.class)
public interface AllBaoJingComponent {
    AllBaoJingActivity inject(AllBaoJingActivity Activity);
}