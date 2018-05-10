package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.OrderComplainPartitionFragment;
import com.yxld.yxchuangxin.ui.activity.goods.contract.OrderComplainPartitionContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.OrderComplainPartitionPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of OrderComplainPartitionFragment, provide field for OrderComplainPartitionFragment
 * @date 2017/06/22 18:31:29
 */
@Module
public class OrderComplainPartitionModule {
    private final OrderComplainPartitionContract.View mView;


    public OrderComplainPartitionModule(OrderComplainPartitionContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public OrderComplainPartitionPresenter provideOrderComplainPartitionPresenter(HttpAPIWrapper httpAPIWrapper, OrderComplainPartitionFragment mFragment) {
        return new OrderComplainPartitionPresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public OrderComplainPartitionFragment provideOrderComplainPartitionFragment() {
        return (OrderComplainPartitionFragment) mView;
    }
}