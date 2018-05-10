package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.PaySelectActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.PaySelectContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.PaySelectPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of PaySelectActivity, provide field for PaySelectActivity
 * @date 2017/07/07 11:10:46
 */
@Module
public class PaySelectModule {
    private final PaySelectContract.View mView;


    public PaySelectModule(PaySelectContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public PaySelectPresenter providePaySelectPresenter(HttpAPIWrapper httpAPIWrapper, PaySelectActivity mActivity) {
        return new PaySelectPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public PaySelectActivity providePaySelectActivity() {
        return (PaySelectActivity) mView;
    }
}