package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.SJOrder;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.RimTongzhiListActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimTongzhiListContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimTongzhiListPresenter;
import com.yxld.yxchuangxin.ui.adapter.rim.RimTongzhiListAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The moduele of RimTongzhiListActivity, provide field for RimTongzhiListActivity
 * @date 2017/06/17
 */
@Module
public class RimTongzhiListModule {
    private final RimTongzhiListContract.View mView;


    public RimTongzhiListModule(RimTongzhiListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RimTongzhiListPresenter provideRimTongzhiListPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new RimTongzhiListPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public RimTongzhiListActivity provideRimTongzhiListActivity() {
        return (RimTongzhiListActivity) mView;
    }

    @Provides
    @ActivityScope
    public SJOrder provideActivityOrder() {
        SJOrder sjOrder = new SJOrder();
        return sjOrder;
    }

    @Provides
    @ActivityScope
    public RimTongzhiListAdapter provideActivityAdapter(SJOrder order) {
        return new RimTongzhiListAdapter(order.getData());
    }
}