package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.BusinessPushProductActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.BusinessPushProductContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.BusinessPushProductPresenter;
import com.yxld.yxchuangxin.ui.adapter.rim.PushProductHorizenAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The moduele of BusinessPushProductActivity, provide field for BusinessPushProductActivity
 * @date 2017/06/19 11:24:46
 */
@Module
public class BusinessPushProductModule {
    private final BusinessPushProductContract.View mView;


    public BusinessPushProductModule(BusinessPushProductContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public BusinessPushProductPresenter provideBusinessPushProductPresenter(HttpAPIWrapper httpAPIWrapper, BusinessPushProductActivity mActivity) {
        return new BusinessPushProductPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public PushProductHorizenAdapter providePushProductHorizenAdapter() {
        return new PushProductHorizenAdapter(new ArrayList<>());
    }

    @Provides
    @ActivityScope
    public BusinessPushProductActivity provideBusinessPushProductActivity() {
        return (BusinessPushProductActivity) mView;
    }
}