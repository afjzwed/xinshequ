package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.OrderComplainDetailActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.OrderComplainDetailContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.OrderComplainDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of OrderComplainDetailActivity, provide field for OrderComplainDetailActivity
 * @date 2017/07/05 14:52:21
 */
@Module
public class OrderComplainDetailModule {
    private final OrderComplainDetailContract.View mView;


    public OrderComplainDetailModule(OrderComplainDetailContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public OrderComplainDetailPresenter provideOrderComplainDetailPresenter(HttpAPIWrapper httpAPIWrapper, OrderComplainDetailActivity mActivity) {
        return new OrderComplainDetailPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public OrderComplainDetailActivity provideOrderComplainDetailActivity() {
        return (OrderComplainDetailActivity) mView;
    }
}