package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CarList;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.CarManageActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.CarManageContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.CarManagePresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.CarManageAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of CarManageActivity, provide field for CarManageActivity
 * @date 2017/06/08
 */
@Module
public class CarManageModule {
    private final CarManageContract.View mView;


    public CarManageModule(CarManageContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CarManagePresenter provideCarManagePresenter(HttpAPIWrapper httpAPIWrapper, CarManageActivity activity) {
        return new CarManagePresenter(httpAPIWrapper, mView, activity);
    }

    @Provides
    @ActivityScope
    public List<CarList.DataBean> provideCarList() {
        List<CarList.DataBean> list = new ArrayList<>();
        return list;
    }

    @Provides
    @ActivityScope
    public CarManageAdapter provideCarManageAdapter(List<CarList.DataBean> list) {
        return new CarManageAdapter(list);
    }
    @Provides
    @ActivityScope
    public CarManageActivity provideCarManageActivity() {
        return (CarManageActivity) mView;
    }
}