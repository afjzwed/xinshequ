package com.yxld.yxchuangxin.ui.activity.mine.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.mine.FingerProveActivity;
import com.yxld.yxchuangxin.ui.activity.mine.contract.FingerProveContract;
import com.yxld.yxchuangxin.ui.activity.mine.presenter.FingerProvePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: The moduele of FingerProveActivity, provide field for FingerProveActivity
 * @date 2018/04/03 10:11:18
 */
@Module
public class FingerProveModule {
    private final FingerProveContract.View mView;


    public FingerProveModule(FingerProveContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FingerProvePresenter provideFingerProvePresenter(HttpAPIWrapper httpAPIWrapper, FingerProveActivity mActivity) {
        return new FingerProvePresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public FingerProveActivity provideFingerProveActivity() {
        return (FingerProveActivity) mView;
    }
}