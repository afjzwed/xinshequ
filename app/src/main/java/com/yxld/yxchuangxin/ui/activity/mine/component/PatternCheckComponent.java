package com.yxld.yxchuangxin.ui.activity.mine.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.mine.PatternCheckActivity;
import com.yxld.yxchuangxin.ui.activity.mine.module.PatternCheckModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: The component for PatternCheckActivity
 * @date 2018/04/04 14:08:08
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = PatternCheckModule.class)
public interface PatternCheckComponent {
    PatternCheckActivity inject(PatternCheckActivity Activity);
}