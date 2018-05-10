package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.SecondShopCartActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.SecondShopCartContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.SecondShopCartPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of SecondShopCartActivity, provide field for SecondShopCartActivity
 * @date 2017/07/05 14:05:30
 */
@Module
public class SecondShopCartModule {
    private final SecondShopCartContract.View mView;


    public SecondShopCartModule(SecondShopCartContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public SecondShopCartPresenter provideSecondShopCartPresenter(HttpAPIWrapper httpAPIWrapper, SecondShopCartActivity mActivity) {
        return new SecondShopCartPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public SecondShopCartActivity provideSecondShopCartActivity() {
        return (SecondShopCartActivity) mView;
    }
}