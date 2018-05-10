package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.MenJinActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MenJinContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.MenJinPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of MenJinActivity, provide field for MenJinActivity
 * @date 2017/06/06
 */
@Module
public class MenJinModule {
    private final MenJinContract.View mView;


    public MenJinModule(MenJinContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MenJinPresenter provideMenJinPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new MenJinPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public MenJinActivity provideMenJinActivity() {
        return (MenJinActivity) mView;
    }
}