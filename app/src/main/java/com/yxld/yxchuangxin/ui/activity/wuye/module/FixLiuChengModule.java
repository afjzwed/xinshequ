package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.FixLiuChengActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.FixLiuChengContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.FixLiuChengPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of FixLiuChengActivity, provide field for FixLiuChengActivity
 * @date 2018/05/14 10:23:41
 */
@Module
public class FixLiuChengModule {
    private final FixLiuChengContract.View mView;


    public FixLiuChengModule(FixLiuChengContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FixLiuChengPresenter provideFixLiuChengPresenter(HttpAPIWrapper httpAPIWrapper, FixLiuChengActivity mActivity) {
        return new FixLiuChengPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public FixLiuChengActivity provideFixLiuChengActivity() {
        return (FixLiuChengActivity) mView;
    }
}