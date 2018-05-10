package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.OrderListPartitionFragment;
import com.yxld.yxchuangxin.ui.activity.goods.contract.OrderListPartitionContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.OrderListPartitionPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of OrderListPartitionFragment, provide field for OrderListPartitionFragment
 * @date 2017/06/21 17:10:04
 */
@Module
public class OrderListPartitionModule {
    private final OrderListPartitionContract.View mView;


    public OrderListPartitionModule(OrderListPartitionContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public OrderListPartitionPresenter provideOrderListPartitionPresenter(HttpAPIWrapper httpAPIWrapper, OrderListPartitionFragment mFragment) {
        return new OrderListPartitionPresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public OrderListPartitionFragment provideOrderListPartitionFragment() {
        return (OrderListPartitionFragment) mView;
    }
}