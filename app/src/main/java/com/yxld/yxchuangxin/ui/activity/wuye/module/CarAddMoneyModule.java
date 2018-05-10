package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.CarAddMoneyActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.CarAddMoneyContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.CarAddMoneyPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of CarAddMoneyActivity, provide field for CarAddMoneyActivity
 * @date 2017/07/06 15:05:41
 */
@Module
public class CarAddMoneyModule {
    private final CarAddMoneyContract.View mView;


    public CarAddMoneyModule(CarAddMoneyContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CarAddMoneyPresenter provideCarAddMoneyPresenter(HttpAPIWrapper httpAPIWrapper, CarAddMoneyActivity mActivity) {
        return new CarAddMoneyPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public CarAddMoneyActivity provideCarAddMoneyActivity() {
        return (CarAddMoneyActivity) mView;
    }
}