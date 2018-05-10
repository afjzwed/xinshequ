package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.BusinessOrderDetailActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.BusinessOrderDetailContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.BusinessOrderDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The moduele of BusinessOrderDetailActivity, provide field for BusinessOrderDetailActivity
 * @date 2017/06/20 14:05:57
 */
@Module
public class BusinessOrderDetailModule {
    private final BusinessOrderDetailContract.View mView;


    public BusinessOrderDetailModule(BusinessOrderDetailContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public BusinessOrderDetailPresenter provideBusinessOrderDetailPresenter(HttpAPIWrapper httpAPIWrapper, BusinessOrderDetailActivity mActivity) {
        return new BusinessOrderDetailPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public BusinessOrderDetailActivity provideBusinessOrderDetailActivity() {
        return (BusinessOrderDetailActivity) mView;
    }
}