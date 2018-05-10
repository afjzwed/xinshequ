package com.yxld.yxchuangxin.ui.activity.mine.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.mine.MultiAccountActivity;
import com.yxld.yxchuangxin.ui.activity.mine.module.MultiAccountModule;

import dagger.Component;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: The component for MultiAccountActivity
 * @date 2017/10/11 09:31:27
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MultiAccountModule.class)
public interface MultiAccountComponent {
    MultiAccountActivity inject(MultiAccountActivity Activity);
}