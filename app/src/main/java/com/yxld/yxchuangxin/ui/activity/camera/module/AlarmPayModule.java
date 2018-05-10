package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.AlarmPayActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.AlarmPayContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.AlarmPayPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of AlarmPayActivity, provide field for AlarmPayActivity
 * @date 2017/09/28 09:59:31
 */
@Module
public class AlarmPayModule {
    private final AlarmPayContract.View mView;


    public AlarmPayModule(AlarmPayContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public AlarmPayPresenter provideCameraPayPresenter(HttpAPIWrapper httpAPIWrapper, AlarmPayActivity mActivity) {
        return new AlarmPayPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public AlarmPayActivity provideCameraPayActivity() {
        return (AlarmPayActivity) mView;
    }
}