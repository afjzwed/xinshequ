package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.OrderListActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.OrderListContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.OrderListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of OrderListActivity, provide field for OrderListActivity
 * @date 2017/06/21 17:03:40
 */
@Module
public class OrderListModule {
    private final OrderListContract.View mView;


    public OrderListModule(OrderListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public OrderListPresenter provideOrderListPresenter(HttpAPIWrapper httpAPIWrapper, OrderListActivity mActivity) {
        return new OrderListPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public OrderListActivity provideOrderListActivity() {
        return (OrderListActivity) mView;
    }
}