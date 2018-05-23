package com.yxld.yxchuangxin.ui.activity.door.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.door.InboundActivity;
import com.yxld.yxchuangxin.ui.activity.door.contract.InboundContract;
import com.yxld.yxchuangxin.ui.activity.door.presenter.InboundPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.door
 * @Description: The moduele of InboundActivity, provide field for InboundActivity
 * @date 2018/04/19 14:12:55
 */
@Module
public class InboundModule {
    private final InboundContract.View mView;


    public InboundModule(InboundContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public InboundPresenter provideInboundPresenter(HttpAPIWrapper httpAPIWrapper, InboundActivity mActivity) {
        return new InboundPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public InboundActivity provideInboundActivity() {
        return (InboundActivity) mView;
    }
}