package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.HouxuanListActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.HouxuanListContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.HouxuanListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The moduele of HouxuanListActivity, provide field for HouxuanListActivity
 * @date 2018/11/14 09:53:34
 */
@Module
public class HouxuanListModule {
    private final HouxuanListContract.View mView;


    public HouxuanListModule(HouxuanListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public HouxuanListPresenter provideHouxuanListPresenter(HttpAPIWrapper httpAPIWrapper, HouxuanListActivity mActivity) {
        return new HouxuanListPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public HouxuanListActivity provideHouxuanListActivity() {
        return (HouxuanListActivity) mView;
    }
}