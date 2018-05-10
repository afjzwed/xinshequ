package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.SJOrder;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimPublicOrderListFragment;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimPublicOrderListContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimPublicOrderListPresenter;
import com.yxld.yxchuangxin.ui.adapter.rim.RimOrderListAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The moduele of RimPublicOrderListFragment, provide field for RimPublicOrderListFragment
 * @date 2017/06/17
 */
@Module
public class RimPublicOrderListModule {
    private final RimPublicOrderListContract.View mView;


    public RimPublicOrderListModule(RimPublicOrderListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RimPublicOrderListPresenter provideRimPublicOrderListPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new RimPublicOrderListPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public RimPublicOrderListFragment provideRimPublicOrderListFragment() {
        return (RimPublicOrderListFragment) mView;
    }

    @Provides
    @ActivityScope
    public SJOrder provideActivityOrder() {
        SJOrder sjOrder = new SJOrder();
        return sjOrder;
    }

    @Provides
    @ActivityScope
    public RimOrderListAdapter provideActivityAdapter(SJOrder order) {
        return new RimOrderListAdapter(order.getData());
    }
}