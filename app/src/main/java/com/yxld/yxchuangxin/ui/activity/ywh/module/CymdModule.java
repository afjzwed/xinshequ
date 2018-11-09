package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.CymdActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.CymdContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.CymdPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The moduele of CymdActivity, provide field for CymdActivity
 * @date 2018/11/09 09:18:52
 */
@Module
public class CymdModule {
    private final CymdContract.View mView;


    public CymdModule(CymdContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CymdPresenter provideCymdPresenter(HttpAPIWrapper httpAPIWrapper, CymdActivity mActivity) {
        return new CymdPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public CymdActivity provideCymdActivity() {
        return (CymdActivity) mView;
    }
}