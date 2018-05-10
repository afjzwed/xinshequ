package com.yxld.yxchuangxin.ui.activity.main.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.ScanActivityActivity;
import com.yxld.yxchuangxin.ui.activity.main.contract.ScanActivityContract;
import com.yxld.yxchuangxin.ui.activity.main.presenter.ScanActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: The moduele of ScanActivityActivity, provide field for ScanActivityActivity
 * @date 2018/03/02 09:11:52
 */
@Module
public class ScanActivityModule {
    private final ScanActivityContract.View mView;


    public ScanActivityModule(ScanActivityContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ScanActivityPresenter provideScanActivityPresenter(HttpAPIWrapper httpAPIWrapper, ScanActivityActivity mActivity) {
        return new ScanActivityPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public ScanActivityActivity provideScanActivityActivity() {
        return (ScanActivityActivity) mView;
    }
}