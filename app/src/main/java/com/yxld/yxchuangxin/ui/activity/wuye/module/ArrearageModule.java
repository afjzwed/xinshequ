package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.ArrearageFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.ArrearageContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.ArrearagePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of ArrearageFragment, provide field for ArrearageFragment
 * @date 2017/07/01 13:46:50
 */
@Module
public class ArrearageModule {
    private final ArrearageContract.View mView;


    public ArrearageModule(ArrearageContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ArrearagePresenter provideArrearagePresenter(HttpAPIWrapper httpAPIWrapper, ArrearageFragment mFragment) {
        return new ArrearagePresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public ArrearageFragment provideArrearageFragment() {
        return (ArrearageFragment) mView;
    }
}