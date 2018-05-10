package com.yxld.yxchuangxin.ui.activity.common.module;

import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyCommon;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.common.AisleActivity;
import com.yxld.yxchuangxin.ui.activity.common.contract.AisleContract;
import com.yxld.yxchuangxin.ui.activity.common.presenter.AislePresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.AisleAdapter;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.commom
 * @Description: The moduele of AisleActivity, provide field for AisleActivity
 * @date 2017/06/08
 */
@Module
public class AisleModule {
    private final AisleContract.View mView;


    public AisleModule(AisleContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public AislePresenter provideAislePresenter(HttpAPIWrapper httpAPIWrapper, AisleActivity activity) {
        return new AislePresenter(httpAPIWrapper, mView, activity);
    }

    @Provides
    @ActivityScope
    public List<CxwyCommon.DataBean.CvoListBean> provideList() {
        return Contains.cvoListBean;
    }
    @Provides
    @ActivityScope
    public AisleAdapter provideAisleAdapter(List<CxwyCommon.DataBean.CvoListBean> list) {
        return new AisleAdapter(list);
    }
    @Provides
    @ActivityScope
    public AisleActivity provideAisleActivity() {
        return (AisleActivity) mView;
    }
}