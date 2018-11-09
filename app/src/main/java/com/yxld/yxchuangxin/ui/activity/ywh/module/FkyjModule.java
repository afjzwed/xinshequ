package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.FkyjActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.FkyjContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.FkyjPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The moduele of FkyjActivity, provide field for FkyjActivity
 * @date 2018/11/09 10:03:28
 */
@Module
public class FkyjModule {
    private final FkyjContract.View mView;


    public FkyjModule(FkyjContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FkyjPresenter provideFkyjPresenter(HttpAPIWrapper httpAPIWrapper, FkyjActivity mActivity) {
        return new FkyjPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public FkyjActivity provideFkyjActivity() {
        return (FkyjActivity) mView;
    }
}