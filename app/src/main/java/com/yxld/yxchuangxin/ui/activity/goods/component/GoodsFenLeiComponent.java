package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.GoodsFenLeiActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.GoodsFenLeiModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for GoodsFenLeiActivity
 * @date 2017/10/19 08:58:40
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = GoodsFenLeiModule.class)
public interface GoodsFenLeiComponent {
    GoodsFenLeiActivity inject(GoodsFenLeiActivity Activity);
}