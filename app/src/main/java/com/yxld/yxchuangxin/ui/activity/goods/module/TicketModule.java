package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.TicketActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.TicketContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.TicketPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of TicketActivity, provide field for TicketActivity
 * @date 2017/06/22 10:47:40
 */
@Module
public class TicketModule {
    private final TicketContract.View mView;


    public TicketModule(TicketContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public TicketPresenter provideTicketPresenter(HttpAPIWrapper httpAPIWrapper, TicketActivity mActivity) {
        return new TicketPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public TicketActivity provideTicketActivity() {
        return (TicketActivity) mView;
    }
}