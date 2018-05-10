package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.RecordFilesActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.RecordFilesContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.RecordFilesPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of RecordFilesActivity, provide field for RecordFilesActivity
 * @date 2017/06/21 10:22:29
 */
@Module
public class RecordFilesModule {
    private final RecordFilesContract.View mView;


    public RecordFilesModule(RecordFilesContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RecordFilesPresenter provideRecordFilesPresenter(HttpAPIWrapper httpAPIWrapper, RecordFilesActivity mActivity) {
        return new RecordFilesPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public RecordFilesActivity provideRecordFilesActivity() {
        return (RecordFilesActivity) mView;
    }
}