package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.NormalTypeGoodsListActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.NormalTypeGoodsListContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.NormalTypeGoodsListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of NormalTypeGoodsListActivity, provide field for NormalTypeGoodsListActivity
 * @date 2017/06/16
 */
@Module
public class NormalTypeGoodsListModule {
    private final NormalTypeGoodsListContract.View mView;


    public NormalTypeGoodsListModule(NormalTypeGoodsListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public NormalTypeGoodsListPresenter provideNormalTypeGoodsListPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new NormalTypeGoodsListPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public NormalTypeGoodsListActivity provideNormalTypeGoodsListActivity() {
        return (NormalTypeGoodsListActivity) mView;
    }
}