package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.PaymentFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.PaymentContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.PaymentPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of PaymentFragment, provide field for PaymentFragment
 * @date 2017/07/01 13:45:55
 */
@Module
public class PaymentModule {
    private final PaymentContract.View mView;


    public PaymentModule(PaymentContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public PaymentPresenter providePaymentPresenter(HttpAPIWrapper httpAPIWrapper, PaymentFragment mFragment) {
        return new PaymentPresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public PaymentFragment providePaymentFragment() {
        return (PaymentFragment) mView;
    }
}