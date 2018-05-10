package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.AlarmActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.AlarmContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.AlarmPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of AlarmActivity, provide field for AlarmActivity
 * @date 2017/06/21 10:23:03
 */
@Module
public class AlarmModule {
    private final AlarmContract.View mView;


    public AlarmModule(AlarmContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public AlarmPresenter provideAlarmPresenter(HttpAPIWrapper httpAPIWrapper, AlarmActivity mActivity) {
        return new AlarmPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public AlarmActivity provideAlarmActivity() {
        return (AlarmActivity) mView;
    }
}