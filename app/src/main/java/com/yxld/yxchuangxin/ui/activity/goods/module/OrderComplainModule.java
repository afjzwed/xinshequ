package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.OrderComplainActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.OrderComplainContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.OrderComplainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of OrderComplainActivity, provide field for OrderComplainActivity
 * @date 2017/06/22 18:01:39
 */
@Module
public class OrderComplainModule {
    private final OrderComplainContract.View mView;


    public OrderComplainModule(OrderComplainContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public OrderComplainPresenter provideOrderComplainPresenter(HttpAPIWrapper httpAPIWrapper, OrderComplainActivity mActivity) {
        return new OrderComplainPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public OrderComplainActivity provideOrderComplainActivity() {
        return (OrderComplainActivity) mView;
    }
}