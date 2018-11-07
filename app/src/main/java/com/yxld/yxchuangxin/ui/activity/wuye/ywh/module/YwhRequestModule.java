package com.yxld.yxchuangxin.ui.activity.wuye.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.ywh.YwhRequestActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.ywh.contract.YwhRequestContract;
import com.yxld.yxchuangxin.ui.activity.wuye.ywh.presenter.YwhRequestPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of YwhRequestActivity, provide field for YwhRequestActivity
 * @date 2018/11/07 18:59:19
 */
@Module
public class YwhRequestModule {
    private final YwhRequestContract.View mView;


    public YwhRequestModule(YwhRequestContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public YwhRequestPresenter provideYwhRequestPresenter(HttpAPIWrapper httpAPIWrapper, YwhRequestActivity mActivity) {
        return new YwhRequestPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public YwhRequestActivity provideYwhRequestActivity() {
        return (YwhRequestActivity) mView;
    }
}