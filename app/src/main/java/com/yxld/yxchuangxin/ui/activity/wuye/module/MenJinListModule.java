package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.MenJinListFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MenJinListContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.MenJinListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of MenJinListFragment, provide field for MenJinListFragment
 * @date 2018/05/26 13:35:55
 */
@Module
public class MenJinListModule {
    private final MenJinListContract.View mView;


    public MenJinListModule(MenJinListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MenJinListPresenter provideMenJinListPresenter(HttpAPIWrapper httpAPIWrapper, MenJinListFragment mFragment) {
        return new MenJinListPresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public MenJinListFragment provideMenJinListFragment() {
        return (MenJinListFragment) mView;
    }
}