package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.MineFragment;
import com.yxld.yxchuangxin.ui.activity.goods.module.MineModule;

import dagger.Component;

/**
 * @author yuan
 * @Package .ui.activity.goods
 * @Description: The component for MineFragment
 * @date 2017/06/14
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MineModule.class)
public interface MineComponent {
    MineFragment inject(MineFragment Fragment);
}