package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.ResultShowActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.ResultShowContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.ResultShowPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of ResultShowActivity, provide field for ResultShowActivity
 * @date 2018/11/07 20:09:47
 */
@Module
public class ResultShowModule {
    private final ResultShowContract.View mView;


    public ResultShowModule(ResultShowContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ResultShowPresenter provideResultShowPresenter(HttpAPIWrapper httpAPIWrapper, ResultShowActivity mActivity) {
        return new ResultShowPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public ResultShowActivity provideResultShowActivity() {
        return (ResultShowActivity) mView;
    }
}