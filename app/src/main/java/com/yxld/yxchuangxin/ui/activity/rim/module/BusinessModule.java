package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.BusinessActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.BusinessContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.BusinessPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The moduele of BusinessActivity, provide field for BusinessActivity
 * @date 2017/06/17
 */
@Module
public class BusinessModule {
    private final BusinessContract.View mView;


    public BusinessModule(BusinessContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public BusinessPresenter provideBusinessPresenter(HttpAPIWrapper httpAPIWrapper, BusinessActivity activity) {
        return new BusinessPresenter(httpAPIWrapper, mView, activity);
    }

    @Provides
    @ActivityScope
    public BusinessActivity provideBusinessActivity() {
        return (BusinessActivity) mView;
    }
}