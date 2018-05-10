package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyProductInfo;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.ShopCartFragment;
import com.yxld.yxchuangxin.ui.activity.goods.contract.ShopCartContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.ShopCartPresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.ShopCartListAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author yuan
 * @Package .ui.activity.goods
 * @Description: The moduele of ShopCartFragment, provide field for ShopCartFragment
 * @date 2017/06/14
 */
@Module
public class ShopCartModule {
    private final ShopCartContract.View mView;


    public ShopCartModule(ShopCartContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ShopCartPresenter provideShopCartPresenter(HttpAPIWrapper httpAPIWrapper,ShopCartFragment fragment) {
        return new ShopCartPresenter(httpAPIWrapper, mView,fragment);
    }

    @Provides
    @ActivityScope
    public ShopCartListAdapter provideShopCartListAdapter() {
        return new ShopCartListAdapter(mView, new ArrayList<>());
    }

    @Provides
    @ActivityScope
    public ShopCartFragment provideShopCartFragment() {
        return (ShopCartFragment) mView;
    }
}