package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.TuiJianListActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.TuiJianListContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.TuiJianListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The moduele of TuiJianListActivity, provide field for TuiJianListActivity
 * @date 2018/11/08 10:54:14
 */
@Module
public class TuiJianListModule {
    private final TuiJianListContract.View mView;


    public TuiJianListModule(TuiJianListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public TuiJianListPresenter provideTuiJianListPresenter(HttpAPIWrapper httpAPIWrapper, TuiJianListActivity mActivity) {
        return new TuiJianListPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public TuiJianListActivity provideTuiJianListActivity() {
        return (TuiJianListActivity) mView;
    }
}