package com.yxld.yxchuangxin.ui.activity.rim.component;

import com.yxld.yxchuangxin.application.AppComponent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimShopCommentListActivity;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimShopCommentListModule;

import dagger.Component;

/**
 * @author RimShopCommentList
 * @Package com.yxld.yxchuangxin.ui.activity.rimshopcommentlist
 * @Description: The component for RimShopCommentListActivity
 * @date 2017/12/18 13:46:52
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RimShopCommentListModule.class)
public interface RimShopCommentListComponent {
    RimShopCommentListActivity inject(RimShopCommentListActivity Activity);
}