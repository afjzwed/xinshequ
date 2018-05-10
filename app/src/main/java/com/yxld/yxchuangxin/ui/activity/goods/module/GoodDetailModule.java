package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.GoodDetailActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.GoodDetailContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.GoodDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of GoodDetailActivity, provide field for GoodDetailActivity
 * @date 2017/06/16
 */
@Module
public class GoodDetailModule {
    private final GoodDetailContract.View mView;


    public GoodDetailModule(GoodDetailContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public GoodDetailPresenter provideGoodDetailPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new GoodDetailPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public GoodDetailActivity provideGoodDetailActivity() {
        return (GoodDetailActivity) mView;
    }
}