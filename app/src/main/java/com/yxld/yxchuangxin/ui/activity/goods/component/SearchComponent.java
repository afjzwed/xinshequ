package com.yxld.yxchuangxin.ui.activity.goods.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.SearchActivity;
import com.yxld.yxchuangxin.ui.activity.goods.module.SearchModule;

import dagger.Component;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The component for SearchActivity
 * @date 2017/06/24 10:26:33
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = SearchModule.class)
public interface SearchComponent {
    SearchActivity inject(SearchActivity Activity);
}