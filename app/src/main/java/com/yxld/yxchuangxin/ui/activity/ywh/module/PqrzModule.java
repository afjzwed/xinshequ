package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.PqrzActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.PqrzContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.PqrzPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The moduele of PqrzActivity, provide field for PqrzActivity
 * @date 2018/11/09 08:55:03
 */
@Module
public class PqrzModule {
    private final PqrzContract.View mView;


    public PqrzModule(PqrzContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public PqrzPresenter providePqrzPresenter(HttpAPIWrapper httpAPIWrapper, PqrzActivity mActivity) {
        return new PqrzPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public PqrzActivity providePqrzActivity() {
        return (PqrzActivity) mView;
    }
}