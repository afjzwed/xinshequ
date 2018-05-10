package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.MineShopActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MineShopContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.MineShopPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of MineShopActivity, provide field for MineShopActivity
 * @date 2017/10/19 15:23:24
 */
@Module
public class MineShopModule {
    private final MineShopContract.View mView;


    public MineShopModule(MineShopContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MineShopPresenter provideMineShopPresenter(HttpAPIWrapper httpAPIWrapper, MineShopActivity mActivity) {
        return new MineShopPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public MineShopActivity provideMineShopActivity() {
        return (MineShopActivity) mView;
    }
}