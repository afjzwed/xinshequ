package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.JiaofeiMingxi;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.JiaofeiListActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.JiaofeiListContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.JiaofeiListPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.JiaofeiMingxiAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of JiaofeiListActivity, provide field for JiaofeiListActivity
 * @date 2017/07/01 13:34:37
 */
@Module
public class JiaofeiListModule {
    private final JiaofeiListContract.View mView;


    public JiaofeiListModule(JiaofeiListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public JiaofeiListPresenter provideJiaofeiListPresenter(HttpAPIWrapper httpAPIWrapper, JiaofeiListActivity mActivity) {
        return new JiaofeiListPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public JiaofeiMingxiAdapter provideJiaofeimingxiAdapter() {
        return new JiaofeiMingxiAdapter(new ArrayList<JiaofeiMingxi.HouseBean>());
    }

    @Provides
    @ActivityScope
    public JiaofeiListActivity provideJiaofeiListActivity() {
        return (JiaofeiListActivity) mView;
    }
}