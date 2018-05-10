package com.yxld.yxchuangxin.ui.activity.mine.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.mine.PatternProveActivity;
import com.yxld.yxchuangxin.ui.activity.mine.module.PatternProveModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: The component for PatternProveActivity
 * @date 2018/04/04 11:25:15
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = PatternProveModule.class)
public interface PatternProveComponent {
    PatternProveActivity inject(PatternProveActivity Activity);
}