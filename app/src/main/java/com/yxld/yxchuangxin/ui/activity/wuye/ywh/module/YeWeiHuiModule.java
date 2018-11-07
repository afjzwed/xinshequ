package com.yxld.yxchuangxin.ui.activity.wuye.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.ywh.YeWeiHuiActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.ywh.contract.YeWeiHuiContract;
import com.yxld.yxchuangxin.ui.activity.wuye.ywh.presenter.YeWeiHuiPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of YeWeiHuiActivity, provide field for YeWeiHuiActivity
 * @date 2018/11/07 11:49:57
 */
@Module
public class YeWeiHuiModule {
    private final YeWeiHuiContract.View mView;


    public YeWeiHuiModule(YeWeiHuiContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public YeWeiHuiPresenter provideYeWeiHuiPresenter(HttpAPIWrapper httpAPIWrapper, YeWeiHuiActivity mActivity) {
        return new YeWeiHuiPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public YeWeiHuiActivity provideYeWeiHuiActivity() {
        return (YeWeiHuiActivity) mView;
    }
}