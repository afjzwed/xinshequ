package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.FangwuActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.FangwuContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.FangwuPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of FangwuActivity, provide field for FangwuActivity
 * @date 2017/06/06
 */
@Module
public class FangwuModule {
    private final FangwuContract.View mView;


    public FangwuModule(FangwuContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FangwuPresenter provideFangwuPresenter(HttpAPIWrapper httpAPIWrapper, FangwuActivity activity) {
        return new FangwuPresenter(httpAPIWrapper, mView, activity);
    }

    @Provides
    @ActivityScope
    public FangwuActivity provideFangwuActivity() {
        return (FangwuActivity) mView;
    }
}