package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.OrderListActivity;
import com.yxld.yxchuangxin.ui.activity.goods.PayWaySelectActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.OrderListContract;
import com.yxld.yxchuangxin.ui.activity.goods.contract.PayWaySelectContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.OrderListPresenter;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.PayWaySelectPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/29
 * @descprition:
 */
@Module
public class PayWaySelectModule {
    private final PayWaySelectContract.View mView;


    public PayWaySelectModule(PayWaySelectContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public PayWaySelectPresenter providePayWaySelectPresenter(HttpAPIWrapper httpAPIWrapper, PayWaySelectActivity mActivity) {
        return new PayWaySelectPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public PayWaySelectActivity providePayWaySelectActivity() {
        return (PayWaySelectActivity) mView;
    }
}
