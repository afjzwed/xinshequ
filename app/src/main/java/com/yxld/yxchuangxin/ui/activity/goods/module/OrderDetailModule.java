package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.OrderDetailActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.OrderDetailContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.OrderDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of OrderDetailActivity, provide field for OrderDetailActivity
 * @date 2017/06/22 15:45:54
 */
@Module
public class OrderDetailModule {
    private final OrderDetailContract.View mView;


    public OrderDetailModule(OrderDetailContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public OrderDetailPresenter provideOrderDetailPresenter(HttpAPIWrapper httpAPIWrapper, OrderDetailActivity mActivity) {
        return new OrderDetailPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public OrderDetailActivity provideOrderDetailActivity() {
        return (OrderDetailActivity) mView;
    }
}