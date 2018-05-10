package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.SatisficingActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.SatisficingContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.SatisficingPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of SatisficingActivity, provide field for SatisficingActivity
 * @date 2017/06/20 10:07:42
 */
@Module
public class SatisficingModule {
    private final SatisficingContract.View mView;


    public SatisficingModule(SatisficingContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public SatisficingPresenter provideSatisficingPresenter(HttpAPIWrapper httpAPIWrapper, SatisficingActivity mActivity) {
        return new SatisficingPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public SatisficingActivity provideSatisficingActivity() {
        return (SatisficingActivity) mView;
    }
}