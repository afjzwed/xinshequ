package com.yxld.yxchuangxin.ui.activity.main.module;


import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.ActivityOrder;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.SecondActivity;
import com.yxld.yxchuangxin.ui.activity.main.contract.SecondContract;
import com.yxld.yxchuangxin.ui.activity.main.presenter.SecondPresenter;
import com.yxld.yxchuangxin.ui.adapter.main.ActivityAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hu on 2017/5/16.
 */

@Module
public class SecondModule {
    private final SecondContract.View mView;


    public SecondModule(SecondContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public SecondPresenter provideSecondPresenter(HttpAPIWrapper httpAPIWrapper, SecondActivity secondActivity) {
        return new SecondPresenter(httpAPIWrapper, mView, secondActivity);
    }

    @Provides
    @ActivityScope
    public SecondActivity provideSecondtivity() {
        return (SecondActivity) mView;
    }

    @Provides
    @ActivityScope
    public ActivityOrder provideActivityOrder() {
        ActivityOrder activityOrder = new ActivityOrder();
        activityOrder.setData(new ActivityOrder.DataBean());
        activityOrder.getData().setList(new ArrayList<ActivityOrder.DataBean.ListBean>());
        return activityOrder;
    }

    @Provides
    @ActivityScope
    public ActivityAdapter provideActivityAdapter(SecondActivity activity, ActivityOrder order) {
        return new ActivityAdapter(activity, order.getData().getList());
    }

}
