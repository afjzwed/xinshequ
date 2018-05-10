package com.yxld.yxchuangxin.ui.activity.mine.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.mine.FingerProveActivity;
import com.yxld.yxchuangxin.ui.activity.mine.module.FingerProveModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: The component for FingerProveActivity
 * @date 2018/04/03 10:11:18
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FingerProveModule.class)
public interface FingerProveComponent {
    FingerProveActivity inject(FingerProveActivity Activity);
}