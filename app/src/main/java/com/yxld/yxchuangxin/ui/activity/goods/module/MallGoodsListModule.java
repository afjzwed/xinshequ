package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.MallGoodsListActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MallGoodsListContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.MallGoodsListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of MallGoodsListActivity, provide field for MallGoodsListActivity
 * @date 2017/06/19 14:28:26
 */
@Module
public class MallGoodsListModule {
    private final MallGoodsListContract.View mView;


    public MallGoodsListModule(MallGoodsListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MallGoodsListPresenter provideMallGoodsListPresenter(HttpAPIWrapper httpAPIWrapper, MallGoodsListActivity mActivity) {
        return new MallGoodsListPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public MallGoodsListActivity provideMallGoodsListActivity() {
        return (MallGoodsListActivity) mView;
    }
}