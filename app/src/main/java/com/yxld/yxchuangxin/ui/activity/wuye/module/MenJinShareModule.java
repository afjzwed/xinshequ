package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.MenJinShareFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MenJinShareContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.MenJinSharePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of MenJinShareFragment, provide field for MenJinShareFragment
 * @date 2018/05/26 13:36:35
 */
@Module
public class MenJinShareModule {
    private final MenJinShareContract.View mView;


    public MenJinShareModule(MenJinShareContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MenJinSharePresenter provideMenJinSharePresenter(HttpAPIWrapper httpAPIWrapper, MenJinShareFragment mFragment) {
        return new MenJinSharePresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public MenJinShareFragment provideMenJinShareFragment() {
        return (MenJinShareFragment) mView;
    }
}