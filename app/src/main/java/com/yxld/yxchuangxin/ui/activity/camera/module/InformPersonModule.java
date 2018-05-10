package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.InformPersonActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.InformPersonContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.InformPersonPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of InformPersonActivity, provide field for InformPersonActivity
 * @date 2017/09/19 11:17:32
 */
@Module
public class InformPersonModule {
    private final InformPersonContract.View mView;


    public InformPersonModule(InformPersonContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public InformPersonPresenter provideInformPersonPresenter(HttpAPIWrapper httpAPIWrapper, InformPersonActivity mActivity) {
        return new InformPersonPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public InformPersonActivity provideInformPersonActivity() {
        return (InformPersonActivity) mView;
    }
}