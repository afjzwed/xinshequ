package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.MallFragment;
import com.yxld.yxchuangxin.ui.activity.goods.module.MallModule;
import com.yxld.yxchuangxin.ui.activity.main.module.Mainmodule;

import dagger.Component;

/**
 * @author yuan
 * @Package .ui.activity.goods
 * @Description: The component for MallFragment
 * @date 2017/06/14
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = {MallModule.class})
public interface MallComponent {
    MallFragment inject(MallFragment Fragment);
}