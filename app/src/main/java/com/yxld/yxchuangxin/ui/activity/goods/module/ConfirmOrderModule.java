package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.ConfirmOrderActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.ConfirmOrderContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.ConfirmOrderPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of ConfirmOrderActivity, provide field for ConfirmOrderActivity
 * @date 2017/06/22 11:07:51
 */
@Module
public class ConfirmOrderModule {
    private final ConfirmOrderContract.View mView;


    public ConfirmOrderModule(ConfirmOrderContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ConfirmOrderPresenter provideConfirmOrderPresenter(HttpAPIWrapper httpAPIWrapper, ConfirmOrderActivity mActivity) {
        return new ConfirmOrderPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public ConfirmOrderActivity provideConfirmOrderActivity() {
        return (ConfirmOrderActivity) mView;
    }
}