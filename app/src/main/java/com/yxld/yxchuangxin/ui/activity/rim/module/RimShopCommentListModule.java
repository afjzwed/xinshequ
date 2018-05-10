package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimShopCommentListActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimShopCommentListContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimShopCommentListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author RimShopCommentList
 * @Package com.yxld.yxchuangxin.ui.activity.rimshopcommentlist
 * @Description: The moduele of RimShopCommentListActivity, provide field for RimShopCommentListActivity
 * @date 2017/12/18 13:46:52
 */
@Module
public class RimShopCommentListModule {
    private final RimShopCommentListContract.View mView;


    public RimShopCommentListModule(RimShopCommentListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RimShopCommentListPresenter provideRimShopCommentListPresenter(HttpAPIWrapper httpAPIWrapper, RimShopCommentListActivity mActivity) {
        return new RimShopCommentListPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public RimShopCommentListActivity provideRimShopCommentListActivity() {
        return (RimShopCommentListActivity) mView;
    }
}