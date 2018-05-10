package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CarJiaofeiRecord;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.CarMoneyRecordActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.CarMoneyRecordContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.CarMoneyRecordPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.CarJiaofeiRecordAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of CarMoneyRecordActivity, provide field for CarMoneyRecordActivity
 * @date 2017/07/06 18:00:14
 */
@Module
public class CarMoneyRecordModule {
    private final CarMoneyRecordContract.View mView;


    public CarMoneyRecordModule(CarMoneyRecordContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CarMoneyRecordPresenter provideCarMoneyRecordPresenter(HttpAPIWrapper httpAPIWrapper, CarMoneyRecordActivity mActivity) {
        return new CarMoneyRecordPresenter(httpAPIWrapper, mView, mActivity);
    }
    @Provides
    @ActivityScope
    public CarJiaofeiRecordAdapter provideCarJiaofeiRecordAdapter() {
        return new CarJiaofeiRecordAdapter(new ArrayList<CarJiaofeiRecord.DataBean>());
    }

    @Provides
    @ActivityScope
    public CarMoneyRecordActivity provideCarMoneyRecordActivity() {
        return (CarMoneyRecordActivity) mView;
    }
}