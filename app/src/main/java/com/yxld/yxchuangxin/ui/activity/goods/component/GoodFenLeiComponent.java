package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.GoodFenLeiFragment;
import com.yxld.yxchuangxin.ui.activity.goods.module.GoodFenLeiModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for GoodFenLeiFragment
 * @date 2017/10/19 09:28:17
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = GoodFenLeiModule.class)
public interface GoodFenLeiComponent {
    GoodFenLeiFragment inject(GoodFenLeiFragment Fragment);
}